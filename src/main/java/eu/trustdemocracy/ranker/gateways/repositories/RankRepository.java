package eu.trustdemocracy.ranker.gateways.repositories;

import eu.trustdemocracy.ranker.core.entities.Relationship;
import eu.trustdemocracy.ranker.core.entities.User;
import java.util.Map;
import java.util.UUID;

public interface RankRepository {

  void addLock(long timestamp);

  void enqueueRequest(long timestamp);

  Long dequeueRequest();

  void createRelationship(Relationship relationship);

  void removeRelationship(Relationship relationship);

  void createUser(User user);

  void removeUser(User user);

  Map<UUID, User> getGraph();
}
