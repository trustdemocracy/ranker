package eu.trustdemocracy.ranker.core.interactors.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import eu.trustdemocracy.ranker.core.models.request.UserRequestDTO;
import eu.trustdemocracy.ranker.gateways.FakeRankRepository;
import java.util.UUID;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddUserTest {

  private FakeRankRepository rankRepository;

  @BeforeEach
  public void init() {
    rankRepository = new FakeRankRepository();
  }


  @Test
  public void addUser() {
    val userId = UUID.randomUUID();

    UserRequestDTO requestDTO = new UserRequestDTO()
        .setId(userId);

    Boolean response = new AddUser(rankRepository).execute(requestDTO);

    assertTrue(response);
    assertEquals(1, rankRepository.users.size());
    assertEquals(1, rankRepository.executionRequests.size());
  }

}
