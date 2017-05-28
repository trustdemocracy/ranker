package eu.trustdemocracy.ranker.core.interactors.rank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import eu.trustdemocracy.ranker.core.entities.Relationship;
import eu.trustdemocracy.ranker.core.entities.User;
import eu.trustdemocracy.ranker.gateways.repositories.fake.FakeRankRepository;
import eu.trustdemocracy.ranker.gateways.out.FakeUsersGateway;
import eu.trustdemocracy.ranker.gateways.out.FakeVotesGateway;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculateRankTest {

  private FakeRankRepository rankRepository;
  private FakeUsersGateway usersGateway;
  private FakeVotesGateway votesGateway;

  private Map<String, User> users = new HashMap<>();

  @BeforeEach
  public void init() {
    rankRepository = new FakeRankRepository();
    usersGateway = new FakeUsersGateway();
    votesGateway = new FakeVotesGateway();

    val user1 = new User().setId(UUID.randomUUID());
    users.put("user1", user1);
    rankRepository.createUser(user1);
    val user2 = new User().setId(UUID.randomUUID());
    users.put("user2", user2);
    rankRepository.createUser(user2);
    val user3 = new User().setId(UUID.randomUUID());
    users.put("user3", user3);
    rankRepository.createUser(user3);

    // (user1) <--> (user2) <--> (user3)
    rankRepository.createRelationship(new Relationship()
        .setOriginId(user1.getId())
        .setTargetId(user2.getId()));
    rankRepository.createRelationship(new Relationship()
        .setOriginId(user2.getId())
        .setTargetId(user1.getId()));

    rankRepository.createRelationship(new Relationship()
        .setOriginId(user3.getId())
        .setTargetId(user2.getId()));
    rankRepository.createRelationship(new Relationship()
        .setOriginId(user2.getId())
        .setTargetId(user3.getId()));
  }


  @Test
  public void calculatePageRank() {
    long lastCalculatedTime = System.currentTimeMillis();
    new CalculateRank(rankRepository, usersGateway, votesGateway).execute(null);

    assertTrue(lastCalculatedTime <= usersGateway.lastCalculatedTime);
    assertTrue(lastCalculatedTime <= votesGateway.lastCalculatedTime);

    val user1 = users.get("user1");
    val user2 = users.get("user2");
    val user3 = users.get("user3");

    val rank1 = usersGateway.users.get(user1.getId());
    val rank2 = usersGateway.users.get(user2.getId());
    val rank3 = usersGateway.users.get(user3.getId());

    assertEquals(1, rank1 + rank2 + rank3);

    assertEquals(0.258, rank1, 0.001);
    assertEquals(0.484, rank2, 0.001);
    assertEquals(0.258, rank3, 0.001);

    assertEquals(0.258, votesGateway.users.get(user1.getId()), 0.001);
    assertEquals(0.484, votesGateway.users.get(user2.getId()), 0.001);
    assertEquals(0.258, votesGateway.users.get(user3.getId()), 0.001);
  }

}
