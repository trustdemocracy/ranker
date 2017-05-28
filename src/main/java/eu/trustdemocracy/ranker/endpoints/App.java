package eu.trustdemocracy.ranker.endpoints;

import eu.trustdemocracy.ranker.endpoints.util.Runner;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import lombok.val;

public class App extends AbstractVerticle {

  private static final Logger LOG = LoggerFactory.getLogger(App.class);
  private static final int DEFAULT_PORT = 8080;

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

  private void launchScheduler() {
    vertx.executeBlocking(future -> {
      LOG.info("Scheduler started");

      while (true) {
        if (stopScheduler) {
          break;
        }

        LOG.info("Running calculation...");
        try {
          Thread.sleep(10000);
        } catch (InterruptedException ignored) {}
      }

      future.complete();
    }, result -> LOG.info("Scheduler stopped"));
  }

}
