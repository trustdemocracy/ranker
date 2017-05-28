package eu.trustdemocracy.ranker.core.interactors.rank;

import eu.trustdemocracy.ranker.core.entities.User;
import eu.trustdemocracy.ranker.core.interactors.Interactor;
import eu.trustdemocracy.ranker.core.interactors.rank.ranking.PageRankStrategy;
import eu.trustdemocracy.ranker.core.interactors.rank.ranking.RankingStrategy;
import eu.trustdemocracy.ranker.gateways.RankRepository;
import eu.trustdemocracy.ranker.gateways.UsersGateway;
import eu.trustdemocracy.ranker.gateways.VotesGateway;
import java.util.Map;
import java.util.UUID;
import lombok.val;

public class CalculateRank implements Interactor<Void, Void> {

  private RankRepository rankRepository;
  private UsersGateway usersGateway;
  private VotesGateway votesGateway;

  private RankingStrategy rankingStrategy = new PageRankStrategy();

  public CalculateRank(
      RankRepository rankRepository,
      UsersGateway usersGateway,
      VotesGateway votesGateway
  ) {
    this.rankRepository = rankRepository;
    this.usersGateway = usersGateway;
    this.votesGateway = votesGateway;
  }

  @Override
  public Void execute(Void aVoid) {
    Map<UUID, User> graph = rankRepository.getGraph();

    long calculatedTime = System.currentTimeMillis();

    val results = rankingStrategy.rank(graph);

    usersGateway.updateRank(results, calculatedTime);
    votesGateway.updateRank(results, calculatedTime);

    return null;
  }
}
