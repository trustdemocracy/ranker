package eu.trustdemocracy.ranker.core.interactors.rank;

import eu.trustdemocracy.ranker.core.interactors.Interactor;
import eu.trustdemocracy.ranker.gateways.repositories.RankRepository;

public class NeedRecalculate implements Interactor<Void, Boolean> {

  private RankRepository rankRepository;

  public NeedRecalculate(RankRepository rankRepository) {
    this.rankRepository = rankRepository;
  }

  @Override
  public Boolean execute(Void aVoid) {
    return rankRepository.dequeueRequest() != null;
  }
}
