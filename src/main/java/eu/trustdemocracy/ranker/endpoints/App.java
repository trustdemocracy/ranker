package eu.trustdemocracy.ranker.endpoints;

import eu.trustdemocracy.ranker.endpoints.controllers.Controller;
import eu.trustdemocracy.ranker.endpoints.controllers.RankController;
import eu.trustdemocracy.ranker.endpoints.controllers.RelationshipController;
import eu.trustdemocracy.ranker.endpoints.controllers.UserController;
import eu.trustdemocracy.ranker.endpoints.util.Runner;
import eu.trustdemocracy.ranker.infrastructure.DefaultInteractorFactory;
import eu.trustdemocracy.ranker.infrastructure.InteractorFactory;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.val;

public class App extends AbstractVerticle {

  private static final Logger LOG = LoggerFactory.getLogger(App.class);
  private static final int DEFAULT_PORT = 8080;

  private static InteractorFactory interactorFactory = DefaultInteractorFactory.getInstance();

  private Router router;

  private boolean stopScheduler;

  public static void main(String... args) {
    Runner.runVerticle(App.class.getName());
  }

  @Override
  public void start() throws Exception {
    val port = config().getInteger("http.port", DEFAULT_PORT);

    vertx.executeBlocking(future -> {
      router = Router.router(vertx);
      router.route().handler(BodyHandler.create());
      registerControllers();

      vertx.createHttpServer()
          .requestHandler(router::accept)
          .listen(port);

      future.complete();
    }, result -> {
      if (result.succeeded()) {
        LOG.info("App listening on port: " + port);
        launchScheduler();
      } else {
        LOG.error("Failed to start verticle", result.cause());
      }
    });
  }

  private void registerControllers() {
    val controllers = Stream.of(
        UserController.class,
        RelationshipController.class,
        RankController.class
    ).collect(Collectors.toCollection(HashSet<Class<? extends Controller>>::new));

    for (val controller : controllers) {
      try {
        val constructor = controller.getConstructor(App.class);
        constructor.newInstance(this);
      } catch (Exception e) {
        LOG.error("Failing to attach controller [" + controller.getName() + "]", e);
      }
    }
  }

  public Router getRouter() {
    return router;
  }

  public InteractorFactory getInteractorFactory() {
    return interactorFactory;
  }

  public static void setInteractorFactory(InteractorFactory interactorFactory) {
    if (interactorFactory == null) {
      throw new NullPointerException("InteractorFactory cannot be null");
    }
    App.interactorFactory = interactorFactory;
  }


  private void launchScheduler() {
    val scheduler = new RankScheduler(this);
    scheduler.start();
  }

}
