package eu.trustdemocracy.ranker.gateways;

public interface RankRepository {

  void addLock(long timestamp);
}
