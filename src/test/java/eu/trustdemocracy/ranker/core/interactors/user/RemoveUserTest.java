package eu.trustdemocracy.ranker.core.interactors.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import eu.trustdemocracy.ranker.core.models.request.UserRequestDTO;
import eu.trustdemocracy.ranker.gateways.FakeUserRepository;
import java.util.UUID;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RemoveUserTest {

  private FakeUserRepository userRepository;

  @BeforeEach
  public void init() {
    userRepository = new FakeUserRepository();
  }


  @Test
  public void removeUser() {
    val userId = UUID.randomUUID();

    UserRequestDTO requestDTO = new UserRequestDTO()
        .setId(userId);

    new AddUser(userRepository).execute(requestDTO);

    assertEquals(1, userRepository.users.size());

    Boolean response = new RemoveUser(userRepository).execute(requestDTO);
    assertTrue(response);
    assertEquals(0, userRepository.users.size());
  }

}
