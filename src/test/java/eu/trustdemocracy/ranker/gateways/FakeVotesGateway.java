package eu.trustdemocracy.ranker.gateways;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FakeVotesGateway implements VotesGateway {

  public Map<UUID, Double> users = new HashMap<>();

  public long lastCalculatedTime;

  @Override
  public void updateRank(Map<UUID, Double> results, long lastCalculatedTime) {
    this.lastCalculatedTime = lastCalculatedTime;
    users = results;
  }
}
