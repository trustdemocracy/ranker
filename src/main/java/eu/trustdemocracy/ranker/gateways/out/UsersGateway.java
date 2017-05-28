package eu.trustdemocracy.ranker.gateways.out;

import java.util.Map;
import java.util.UUID;

public interface UsersGateway {

  void updateRank(Map<UUID, Double> results, long lastCalculatedTime);
}
