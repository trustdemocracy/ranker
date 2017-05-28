package eu.trustdemocracy.ranker.infrastructure;

import eu.trustdemocracy.ranker.core.interactors.user.AddUser;
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

  private RankRepository getRankRepository() {
    return null;
  }
}
