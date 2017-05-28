package eu.trustdemocracy.ranker.core.interactors.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import eu.trustdemocracy.ranker.core.models.request.UserRequestDTO;
import eu.trustdemocracy.ranker.gateways.FakeRankRepository;
import java.util.UUID;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RemoveUserTest {

  private FakeRankRepository rankRepository;

  @BeforeEach
  public void init() {
    rankRepository = new FakeRankRepository();
  }


  @Test
  public void removeUser() {
    val userId = UUID.randomUUID();

    UserRequestDTO requestDTO = new UserRequestDTO()
        .setId(userId);

    new AddUser(rankRepository).execute(requestDTO);

    assertEquals(1, rankRepository.users.size());

    Boolean response = new RemoveUser(rankRepository).execute(requestDTO);
    assertTrue(response);
    assertEquals(0, rankRepository.users.size());
  }

}
