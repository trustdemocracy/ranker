package eu.trustdemocracy.ranker.core.interactors.rank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import eu.trustdemocracy.ranker.core.models.request.LockRequestDTO;
import eu.trustdemocracy.ranker.gateways.repositories.fake.FakeRankRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddLockDateTest {

  private FakeRankRepository rankRepository;

  @BeforeEach
  public void init() {
    this.rankRepository = new FakeRankRepository();
  }

  @Test
  public void addLockDate() {
    LockRequestDTO requestDTO = new LockRequestDTO()
        .setTimestamp(System.currentTimeMillis());

    Boolean response = new AddLockDate(rankRepository).execute(requestDTO);

    assertTrue(response);
    assertEquals(1, rankRepository.locks.size());
  }

}
