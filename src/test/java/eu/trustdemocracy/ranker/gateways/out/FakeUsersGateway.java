package eu.trustdemocracy.ranker.gateways.out;

import eu.trustdemocracy.ranker.gateways.out.UsersGateway;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FakeUsersGateway implements UsersGateway {

  public Map<UUID, Double> users = new HashMap<>();
  public long lastCalculatedTime;

  @Override
  public void updateRank(Map<UUID, Double> results, long lastCalculatedTime) {
    this.lastCalculatedTime = lastCalculatedTime;
    this.users = results;
  }
}
