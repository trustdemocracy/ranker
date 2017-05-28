package eu.trustdemocracy.ranker.gateways;

import eu.trustdemocracy.ranker.core.entities.User;

public interface UserRepository {

  void create(User user);
}
