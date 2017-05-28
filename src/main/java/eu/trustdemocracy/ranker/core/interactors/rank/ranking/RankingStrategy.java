package eu.trustdemocracy.ranker.core.interactors.rank.ranking;

import eu.trustdemocracy.ranker.core.entities.User;
import java.util.Map;
import java.util.UUID;

public interface RankingStrategy {

  Map<UUID, Double> rank(Map<UUID, User> graph);
}
