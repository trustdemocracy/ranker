package eu.trustdemocracy.ranker.core.interactors.rank;

import eu.trustdemocracy.ranker.core.interactors.Interactor;
import eu.trustdemocracy.ranker.gateways.RankRepository;
import eu.trustdemocracy.ranker.gateways.UsersGateway;
import eu.trustdemocracy.ranker.gateways.VotesGateway;

public class CalculateRank implements Interactor<Void, Void> {

  public CalculateRank(
      RankRepository rankRepository,
      UsersGateway usersGateway,
      VotesGateway votesGateway
  ) {
  }

  @Override
  public Void execute(Void aVoid) {
    return null;
  }
}
