package eu.trustdemocracy.ranker.gateways.repositories.fake;

import eu.trustdemocracy.ranker.core.entities.Relationship;
import eu.trustdemocracy.ranker.core.entities.User;
import eu.trustdemocracy.ranker.gateways.repositories.RankRepository;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;

public class FakeRankRepository implements RankRepository {

  public TreeSet<Long> locks = new TreeSet<>();
  public TreeSet<Long> executionRequests = new TreeSet<>();

  public Map<UUID, User> users = new HashMap<>();
  public Set<Relationship> relationships = new HashSet<>();

  @Override
  public void addLock(long timestamp) {
    locks.add(timestamp);
  }

  @Override
  public void enqueueRequest(long timestamp) {
    executionRequests.add(timestamp);
  }

  @Override
  public Long dequeueRequest() {
    if (executionRequests.isEmpty()) {
      return null;
    }
    Long timestamp = executionRequests.pollFirst();
    while (timestamp >= locks.first()) {
      locks.pollFirst();
    }
    return timestamp;
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

    relationships.removeIf(relationship ->
        relationship.getOriginId().equals(user.getId())
            || relationship.getTargetId().equals(user.getId()));
  }

  @Override
  public Map<UUID, User> getGraph() {
    Map<UUID, User> graph = new HashMap<>(users);

    return graph.entrySet().stream()
        .map(user -> {
          Set<UUID> inRelationships = new HashSet<>();
          relationships.forEach(relationship -> {
            if (relationship.getTargetId().equals(user.getKey())) {
              inRelationships.add(relationship.getOriginId());
            } else if (relationship.getOriginId().equals(user.getKey())) {
              int count = user.getValue().getOutRelationshipsCount();
              user.getValue().setOutRelationshipsCount(count + 1);
            }
          });
          user.getValue().setInRelationships(inRelationships);
          return user;
        })
        .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
  }
}
