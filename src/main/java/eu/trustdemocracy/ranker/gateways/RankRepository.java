package eu.trustdemocracy.ranker.gateways;

import eu.trustdemocracy.ranker.core.entities.Relationship;
import eu.trustdemocracy.ranker.core.entities.User;

public interface RankRepository {

  void addLock(long timestamp);

  void addExecutionRequest(long timestamp);

  void createRelationship(Relationship relationship);

  void removeRelationship(Relationship relationship);

  void createUser(User user);

  void removeUser(User user);
}
