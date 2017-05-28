package eu.trustdemocracy.ranker.gateways;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FakeUsersGateway implements UsersGateway {

  public Map<UUID, Double> users = new HashMap<>();
  public long lastCalculatedTime;
}
