package eu.trustdemocracy.ranker.core.interactors.rank;

import eu.trustdemocracy.ranker.core.interactors.Interactor;
import eu.trustdemocracy.ranker.core.models.request.LockRequestDTO;
import eu.trustdemocracy.ranker.gateways.RankRepository;

public class AddLockDate implements Interactor<LockRequestDTO, Boolean> {

  private RankRepository rankRepository;

  public AddLockDate(RankRepository rankRepository) {
    this.rankRepository = rankRepository;
  }

  @Override
  public Boolean execute(LockRequestDTO lockRequestDTO) {
    rankRepository.addLock(lockRequestDTO.getTimestamp());
    return true;
  }
}
