package eu.trustdemocracy.ranker.gateways.repositories;

import com.mongodb.client.MongoDatabase;
import eu.trustdemocracy.ranker.core.entities.Relationship;
import eu.trustdemocracy.ranker.core.entities.User;
import java.util.Map;
import java.util.UUID;

public class MongoRankRepository implements RankRepository {

  private MongoDatabase db;

  public MongoRankRepository(MongoDatabase db) {
    this.db = db;
  }

  @Override
  public void addLock(long timestamp) {

  }

  @Override
  public void addExecutionRequest(long timestamp) {

  }

  @Override
  public void createRelationship(Relationship relationship) {

  }

  @Override
  public void removeRelationship(Relationship relationship) {

  }

  @Override
  public void createUser(User user) {

  }

  @Override
  public void removeUser(User user) {

  }

  @Override
  public Map<UUID, User> getGraph() {
    return null;
  }
}
