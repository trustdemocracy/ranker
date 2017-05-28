package eu.trustdemocracy.ranker.gateways;

import eu.trustdemocracy.ranker.core.entities.Relationship;
import eu.trustdemocracy.ranker.core.entities.User;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class FakeRankRepository implements RankRepository {

  public Set<Long> locks = new HashSet<>();
  public Set<Long> executionRequests = new HashSet<>();

  public Map<UUID, User> users = new HashMap<>();
  public Set<Relationship> relationships = new HashSet<>();

  @Override
  public void addLock(long timestamp) {
    locks.add(timestamp);
  }

  @Override
  public void addExecutionRequest(long timestamp) {
    executionRequests.add(timestamp);
  }

  @Override
  public void createRelationship(Relationship relationship) {
    relationships.add(relationship);
  }

  @Override
  public void removeRelationship(Relationship relationship) {
    relationships.remove(relationship);
  }


  @Override
  public void createUser(User user) {
    user.setRank(0.0);
    users.put(user.getId(), user);
  }

  @Override
  public void removeUser(User user) {
    users.remove(user.getId());
  }
}
