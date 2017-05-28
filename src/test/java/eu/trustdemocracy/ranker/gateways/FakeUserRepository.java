package eu.trustdemocracy.ranker.gateways;

import eu.trustdemocracy.ranker.core.entities.User;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FakeUserRepository implements UserRepository {

  public Map<UUID, User> users = new HashMap<>();

  @Override
  public void create(User user) {
    user.setRank(0.0);
    users.put(user.getId(), user);
  }

  @Override
  public void remove(User user) {
    users.remove(user.getId());
  }
}
