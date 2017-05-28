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

      } else {
        LOG.error("Failed to start verticle", result.cause());
      }
    });
  }

}
