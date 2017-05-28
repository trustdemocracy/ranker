package eu.trustdemocracy.ranker.endpoints;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class RankScheduler {

  private static final Logger LOG = LoggerFactory.getLogger(RankScheduler.class);
  private static final int DEFAULT_INTERVAL = 60 * 60;

  private App app;
  private Integer interval;

  public RankScheduler(App app) {
    this.app = app;
  }

  public void start() {
    LOG.info("RankScheduler started");
    app.getVertx().setPeriodic(getRankInterval(), id -> {

      if (app.getInteractorFactory().getNeedRecalculate().execute(null)) {
        LOG.info("Graph has changed. Running graph calculation...");
        app.getInteractorFactory().getCalculateRank().execute(null);
        LOG.info("Graph has been updated...");
      } else {
        LOG.info("No changes in the graph. Nothing to compute...");
      }

    });
  }


  private int getRankInterval() {
    if (interval == null) {
      String customInterval = System.getenv("rank_interval_seconds");
      if (customInterval != null) {
        interval = Integer.valueOf(customInterval) * 1000;
      } else {
        interval = DEFAULT_INTERVAL * 1000;
      }
    }
    return interval;
  }

}
