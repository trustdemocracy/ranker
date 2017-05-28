package eu.trustdemocracy.ranker.gateways;

import java.util.HashSet;
import java.util.Set;

public class FakeRankRepository implements RankRepository {

  public Set<Long> locks = new HashSet<>();
}
