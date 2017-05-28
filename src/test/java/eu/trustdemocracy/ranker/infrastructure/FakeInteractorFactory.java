package eu.trustdemocracy.ranker.infrastructure;

import com.github.fakemongo.Fongo;
import com.mongodb.client.MongoDatabase;
import eu.trustdemocracy.ranker.core.interactors.user.AddUser;
import eu.trustdemocracy.ranker.gateways.repositories.MongoRankRepository;
import eu.trustdemocracy.ranker.gateways.repositories.RankRepository;
import lombok.val;

public class FakeInteractorFactory implements InteractorFactory {

  private MongoDatabase db;

  @Override
  public AddUser getAddUser() {
    return new AddUser(getRankRepository());
  }

  private RankRepository getRankRepository() {
    return new MongoRankRepository(getDB());
  }


  private MongoDatabase getDB() {
    if (db == null) {
      val fongo = new Fongo("test server");
      db = fongo.getDatabase("test_database");
    }
    return db;
  }
}
