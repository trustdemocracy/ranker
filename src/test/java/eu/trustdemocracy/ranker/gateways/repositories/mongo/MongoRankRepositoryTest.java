package eu.trustdemocracy.ranker.gateways.repositories.mongo;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.fakemongo.Fongo;
import com.mongodb.client.MongoDatabase;
import eu.trustdemocracy.ranker.core.entities.Relationship;
import eu.trustdemocracy.ranker.core.entities.User;
import eu.trustdemocracy.ranker.gateways.repositories.MongoRankRepository;
import eu.trustdemocracy.ranker.gateways.repositories.RankRepository;
import java.util.UUID;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MongoRankRepositoryTest {

  private RankRepository rankRepository;
  private MongoDatabase db;

  @BeforeEach
  public void init() {
    val fongo = new Fongo("test server");
    val db = fongo.getDatabase("test_database");
    this.db = db;
    rankRepository = new MongoRankRepository(db);
  }

  @Test
  public void addLock() {
    val collection = db.getCollection("locks");

    val timestamp = System.currentTimeMillis();

    assertEquals(0L, collection.count());
    rankRepository.addLock(timestamp);
    assertEquals(1L, collection.count());

    val doc = collection.find(eq("timestamp", timestamp)).first();
    assertNotNull(doc);
    assertEquals(timestamp, doc.getLong("timestamp"), 0.1);
  }

  @Test
  public void enqueueRequest() {
    val collection = db.getCollection("requests");

    val timestamp = System.currentTimeMillis();

    assertEquals(0L, collection.count());
    rankRepository.enqueueRequest(timestamp);
    assertEquals(1L, collection.count());

    val doc = collection.find(eq("timestamp", timestamp)).first();
    assertNotNull(doc);
    assertEquals(timestamp, doc.getLong("timestamp"), 0.1);
  }

  @Test
  public void createRelationship() {
    val collection = db.getCollection("relationships");
    val relationship = new Relationship()
        .setOriginId(UUID.randomUUID())
        .setTargetId(UUID.randomUUID());

    assertEquals(0L, collection.count());
    rankRepository.createRelationship(relationship);
    assertEquals(1L, collection.count());

    val condition = and(
        eq("originId", relationship.getOriginId().toString()),
        eq("targetId", relationship.getTargetId().toString())
    );
    val doc = collection.find(condition).first();
    assertNotNull(doc);
  }

  @Test
  public void removeRelationship() {
    val collection = db.getCollection("relationships");
    val relationship = new Relationship()
        .setOriginId(UUID.randomUUID())
        .setTargetId(UUID.randomUUID());

    assertEquals(0L, collection.count());
    rankRepository.createRelationship(relationship);
    assertEquals(1L, collection.count());
    rankRepository.removeRelationship(relationship);
    assertEquals(0L, collection.count());
  }

  @Test
  public void createUser() {
    val collection = db.getCollection("users");
    val user = new User()
        .setId(UUID.randomUUID());

    assertEquals(0L, collection.count());
    rankRepository.createUser(user);
    assertEquals(1L, collection.count());

    val doc = collection.find(eq("id", user.getId().toString())).first();
    assertNotNull(doc);
  }

  @Test
  public void removeUser() {
    val collection = db.getCollection("users");
    val user = new User()
        .setId(UUID.randomUUID());

    assertEquals(0L, collection.count());
    rankRepository.createUser(user);
    assertEquals(1L, collection.count());
    rankRepository.removeUser(user);
    assertEquals(0L, collection.count());
  }

  @Test
  public void removeUserAndRelationships() {
    val usersCollection = db.getCollection("users");
    val relationshipsCollection = db.getCollection("relationships");
    val user = new User()
        .setId(UUID.randomUUID());
    val outRelationship = new Relationship()
        .setOriginId(user.getId())
        .setTargetId(UUID.randomUUID());
    val inRelationship = new Relationship()
        .setOriginId(UUID.randomUUID())
        .setTargetId(user.getId());

    assertEquals(0L, usersCollection.count());
    assertEquals(0L, relationshipsCollection.count());
    rankRepository.createUser(user);
    rankRepository.createRelationship(outRelationship);
    rankRepository.createRelationship(inRelationship);

    assertEquals(1L, usersCollection.count());
    assertEquals(2L, relationshipsCollection.count());

    rankRepository.removeUser(user);
    assertEquals(0L, usersCollection.count());
    assertEquals(0L, relationshipsCollection.count());
  }

  @Test
  public void getGraph() {
    val user1 = new User().setId(UUID.randomUUID());
    val user2 = new User().setId(UUID.randomUUID());
    val user3 = new User().setId(UUID.randomUUID());
    val user4 = new User().setId(UUID.randomUUID());

    rankRepository.createUser(user1);
    rankRepository.createUser(user2);
    rankRepository.createUser(user3);
    rankRepository.createUser(user4);

    val relationship1 = new Relationship().setOriginId(user1.getId()).setTargetId(user2.getId());
    val relationship2 = new Relationship().setOriginId(user2.getId()).setTargetId(user3.getId());
    val relationship3 = new Relationship().setOriginId(user1.getId()).setTargetId(user4.getId());
    val relationship4 = new Relationship().setOriginId(user2.getId()).setTargetId(user4.getId());
    val relationship5 = new Relationship().setOriginId(user3.getId()).setTargetId(user4.getId());
    val relationship6 = new Relationship().setOriginId(user1.getId()).setTargetId(user3.getId());
    val relationship7 = new Relationship().setOriginId(user2.getId()).setTargetId(user1.getId());

    rankRepository.createRelationship(relationship1);
    rankRepository.createRelationship(relationship2);
    rankRepository.createRelationship(relationship3);
    rankRepository.createRelationship(relationship4);
    rankRepository.createRelationship(relationship5);
    rankRepository.createRelationship(relationship6);
    rankRepository.createRelationship(relationship7);

    val graph = rankRepository.getGraph();

    assertEquals(3, graph.get(user1.getId()).getOutRelationshipsCount());
    assertTrue(graph.get(user1.getId()).getInRelationships().contains(user2.getId()));

    assertEquals(3, graph.get(user2.getId()).getOutRelationshipsCount());
    assertTrue(graph.get(user2.getId()).getInRelationships().contains(user1.getId()));

    assertEquals(1, graph.get(user3.getId()).getOutRelationshipsCount());
    assertTrue(graph.get(user3.getId()).getInRelationships().contains(user1.getId()));
    assertTrue(graph.get(user3.getId()).getInRelationships().contains(user2.getId()));

    assertEquals(0, graph.get(user4.getId()).getOutRelationshipsCount());
    assertTrue(graph.get(user4.getId()).getInRelationships().contains(user1.getId()));
    assertTrue(graph.get(user4.getId()).getInRelationships().contains(user2.getId()));
    assertTrue(graph.get(user4.getId()).getInRelationships().contains(user3.getId()));
  }
}
