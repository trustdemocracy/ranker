package eu.trustdemocracy.ranker.gateways.repositories;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import eu.trustdemocracy.ranker.core.entities.Relationship;
import eu.trustdemocracy.ranker.core.entities.User;
import java.util.Map;
import java.util.UUID;
import lombok.val;
import org.bson.Document;

public class MongoRankRepository implements RankRepository {

  private MongoDatabase db;

  public MongoRankRepository(MongoDatabase db) {
    this.db = db;
  }

  @Override
  public void addLock(long timestamp) {
    val document = new Document("timestamp", timestamp);
    val condition = eq("timestamp", timestamp);
    val options = new UpdateOptions().upsert(true);
    getLocksCollection().replaceOne(condition, document, options);
  }

  @Override
  public void addExecutionRequest(long timestamp) {
    val document = new Document("timestamp", timestamp);
    val condition = eq("timestamp", timestamp);
    val options = new UpdateOptions().upsert(true);
    getRequestsCollection().replaceOne(condition, document, options);
  }

  @Override
  public void createRelationship(Relationship relationship) {
    val originId = relationship.getOriginId().toString();
    val targetId = relationship.getTargetId().toString();

    val document = new Document("originId", originId)
        .append("targetId", targetId);

    val condition = and(
        eq("originId", originId),
        eq("targetId", targetId)
    );
    val options = new UpdateOptions().upsert(true);
    getRelationshipsCollection().replaceOne(condition, document, options);
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

  private MongoCollection<Document> getLocksCollection() {
    return this.db.getCollection("locks");
  }

  private MongoCollection<Document> getRequestsCollection() {
    return this.db.getCollection("requests");
  }

  private MongoCollection<Document> getRelationshipsCollection() {
    return this.db.getCollection("relationships");
  }
}
