package eu.trustdemocracy.ranker.infrastructure;

import eu.trustdemocracy.ranker.core.interactors.user.AddUser;

public interface InteractorFactory {

  AddUser getAddUser();
}
