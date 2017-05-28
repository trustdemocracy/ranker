package eu.trustdemocracy.ranker.infrastructure;

import com.github.fakemongo.Fongo;
import com.mongodb.client.MongoDatabase;
import eu.trustdemocracy.ranker.core.interactors.rank.AddLockDate;
import eu.trustdemocracy.ranker.core.interactors.rank.CalculateRank;
import eu.trustdemocracy.ranker.core.interactors.relationship.AddRelationship;
import eu.trustdemocracy.ranker.core.interactors.relationship.RemoveRelationship;
import eu.trustdemocracy.ranker.core.interactors.user.AddUser;
import eu.trustdemocracy.ranker.core.interactors.user.RemoveUser;
import eu.trustdemocracy.ranker.gateways.out.FakeUsersGateway;
import eu.trustdemocracy.ranker.gateways.out.FakeVotesGateway;
import eu.trustdemocracy.ranker.gateways.out.UsersGateway;
import eu.trustdemocracy.ranker.gateways.out.VotesGateway;
import eu.trustdemocracy.ranker.gateways.repositories.MongoRankRepository;
import eu.trustdemocracy.ranker.gateways.repositories.RankRepository;
import lombok.val;

public class FakeInteractorFactory implements InteractorFactory {

  private MongoDatabase db;

  @Override
  public AddUser getAddUser() {
    return new AddUser(getRankRepository());
  }

  @Override
  public RemoveUser getRemoveUser() {
    return new RemoveUser(getRankRepository());
  }

  @Override
  public AddRelationship getAddRelationship() {
    return new AddRelationship(getRankRepository());
  }

  @Override
  public RemoveRelationship getRemoveRelationship() {
    return new RemoveRelationship(getRankRepository());
  }

  @Override
  public AddLockDate getAddLockDate() {
    return new AddLockDate(getRankRepository());
  }

  @Override
  public CalculateRank getCalculateRank() {
    return new CalculateRank(getRankRepository(), getUsersGateway(), getVotesGateway());
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

  public VotesGateway getVotesGateway() {
    return new FakeVotesGateway();
  }

  public UsersGateway getUsersGateway() {
    return new FakeUsersGateway();
  }
}
