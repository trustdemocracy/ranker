package eu.trustdemocracy.ranker.core.interactors.rank.ranking;

import eu.trustdemocracy.ranker.core.entities.User;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.val;

public class PageRankStrategy implements RankingStrategy {

  private Map<UUID, Double> currentRank = new HashMap<>();
  private Map<UUID, User> graph;

  private static final float DELTA = 0.0001f;
  private static final float FACTOR = 0.825f;
  private static final int MAX_ITERATIONS = 300;

  private int graphSize;

  @Override
  public Map<UUID, Double> rank(Map<UUID, User> graph) {
    this.graph = graph;
    graphSize = graph.size();

    initTempMap();

    for (int i = 0; i < MAX_ITERATIONS; i++) {
      Map<UUID, Double> lastRank = new HashMap<>(currentRank);

      for (val user : graph.values()) {
        Double userRank = 0.0;
        for (val inId : user.getInRelationships()) {
          userRank += lastRank.get(inId) / graph.get(inId).getOutRelationshipsCount();
        }
        userRank = (1 - FACTOR) + FACTOR * userRank;
        currentRank.put(user.getId(), userRank);
      }

      val firstOldRank = lastRank.values().iterator().next();
      val firstNewRank = currentRank.values().iterator().next();

      if (Math.abs(firstOldRank / graphSize - firstNewRank / graphSize) <= DELTA) {
        break;
      }
    }

    normalize();

    return currentRank;
  }

  private void normalize() {
    Double graphTotal = 0.0;
    for (val rank : currentRank.entrySet()) {
      graphTotal += rank.getValue();
    }

    for (val rank : currentRank.entrySet()) {
      currentRank.put(rank.getKey(), rank.getValue() / graphTotal);
    }
  }

  private void initTempMap() {
    for (val id : graph.keySet()) {
      currentRank.put(id, 1.0);
    }
  }

}
