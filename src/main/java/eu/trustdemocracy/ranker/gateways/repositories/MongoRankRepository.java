package eu.trustdemocracy.ranker.gateways.repositories;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;

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
    val originId = relationship.getOriginId().toString();
    val targetId = relationship.getTargetId().toString();

    val condition = and(
        eq("originId", originId),
        eq("targetId", targetId)
    );
    getRelationshipsCollection().deleteOne(condition);
  }

  @Override
  public void createUser(User user) {
    val id = user.getId().toString();
    val document = new Document("id", id);
    val condition = eq("id", id);
    val options = new UpdateOptions().upsert(true);
    getUsersCollection().replaceOne(condition, document, options);
  }

  @Override
  public void removeUser(User user) {
    val id = user.getId().toString();
    val condition = eq("id", id);
    getUsersCollection().deleteOne(condition);

    val relationshipCondition = or(
        eq("originId", id),
        eq("targetId", id)
    );
    getRelationshipsCollection().deleteMany(relationshipCondition);
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

  private MongoCollection<Document> getUsersCollection() {
    return this.db.getCollection("users");
  }
}
