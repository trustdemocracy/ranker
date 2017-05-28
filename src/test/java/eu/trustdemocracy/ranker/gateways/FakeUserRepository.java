package eu.trustdemocracy.ranker.gateways;

import eu.trustdemocracy.ranker.core.entities.User;
import java.util.HashSet;
import java.util.Set;

public class FakeUserRepository implements UserRepository {

  public Set<User> users = new HashSet<>();
}
