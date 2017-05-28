package eu.trustdemocracy.ranker.infrastructure;

import eu.trustdemocracy.ranker.core.interactors.rank.AddLockDate;
import eu.trustdemocracy.ranker.core.interactors.relationship.AddRelationship;
import eu.trustdemocracy.ranker.core.interactors.relationship.RemoveRelationship;
import eu.trustdemocracy.ranker.core.interactors.user.AddUser;
import eu.trustdemocracy.ranker.core.interactors.user.RemoveUser;
import eu.trustdemocracy.ranker.gateways.repositories.RankRepository;

public class DefaultInteractorFactory implements InteractorFactory {

  private static DefaultInteractorFactory instance;

  private DefaultInteractorFactory() {
  }

  public static DefaultInteractorFactory getInstance() {
    if (instance == null) {
      instance = new DefaultInteractorFactory();
    }
    return instance;
  }

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

  private RankRepository getRankRepository() {
    return null;
  }
}
