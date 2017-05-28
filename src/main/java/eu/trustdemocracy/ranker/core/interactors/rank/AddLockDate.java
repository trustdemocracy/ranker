package eu.trustdemocracy.ranker.core.interactors.rank;

import eu.trustdemocracy.ranker.core.interactors.Interactor;
import eu.trustdemocracy.ranker.core.models.request.LockRequestDTO;
import eu.trustdemocracy.ranker.gateways.RankRepository;

public class AddLockDate implements Interactor<LockRequestDTO, Boolean> {

  public AddLockDate(RankRepository rankRepository) {
  }

  @Override
  public Boolean execute(LockRequestDTO lockRequestDTO) {
    return null;
  }
}
