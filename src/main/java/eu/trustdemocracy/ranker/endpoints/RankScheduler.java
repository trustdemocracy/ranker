package eu.trustdemocracy.ranker.endpoints;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class RankScheduler {

  private static final Logger LOG = LoggerFactory.getLogger(RankScheduler.class);
  private static final int DEFAULT_INTERVAL = 60;

  private App app;
  private Integer interval;
  private boolean shouldStop;

  public RankScheduler(App app) {
    this.app = app;
  }

  public void start() {
    app.getVertx().executeBlocking(future -> {
      LOG.info("RankScheduler started");

      while (true) {
        if (shouldStop) {
          break;
        }
        
        LOG.info("Running graph calculation...");
        try {
          Thread.sleep(getRankInterval());
        } catch (InterruptedException ignored) {
        }
      }

      future.complete();
    }, result -> LOG.info("RankScheduler stopped"));
  }

  public void stop() {
    this.shouldStop = true;
  }

  private int getRankInterval() {
    if (interval == null) {
      String customInterval = System.getenv("rank_interval_seconds");
      if (customInterval != null) {
        interval = Integer.valueOf(customInterval) * 1000;
      } else {
        interval = 60 * 1000;
      }
    }
    return interval;
  }

}
