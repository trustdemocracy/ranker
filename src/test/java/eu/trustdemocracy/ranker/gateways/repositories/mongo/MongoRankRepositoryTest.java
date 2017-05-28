package eu.trustdemocracy.ranker.gateways.repositories.mongo;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.fakemongo.Fongo;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import eu.trustdemocracy.ranker.gateways.repositories.MongoRankRepository;
import eu.trustdemocracy.ranker.gateways.repositories.RankRepository;
import lombok.val;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MongoRankRepositoryTest {

  private MongoCollection<Document> collection;
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

}
