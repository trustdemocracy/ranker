package eu.trustdemocracy.ranker.infrastructure;

import eu.trustdemocracy.ranker.core.interactors.rank.AddLockDate;
import eu.trustdemocracy.ranker.core.interactors.rank.CalculateRank;
import eu.trustdemocracy.ranker.core.interactors.rank.NeedRecalculate;
import eu.trustdemocracy.ranker.core.interactors.relationship.AddRelationship;
import eu.trustdemocracy.ranker.core.interactors.relationship.RemoveRelationship;
import eu.trustdemocracy.ranker.core.interactors.user.AddUser;
import eu.trustdemocracy.ranker.core.interactors.user.RemoveUser;

public interface InteractorFactory {

  AddUser getAddUser();

  RemoveUser getRemoveUser();

  AddRelationship getAddRelationship();

  RemoveRelationship getRemoveRelationship();

  AddLockDate getAddLockDate();

  CalculateRank getCalculateRank();

  NeedRecalculate getNeedRecalculate();
}
