package eu.trustdemocracy.ranker.core.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import eu.trustdemocracy.ranker.core.models.request.UserRequestDTO;
import eu.trustdemocracy.ranker.gateways.FakeUserRepository;
import java.util.UUID;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddUserTest {

  private FakeUserRepository userRepository;

  @BeforeEach
  public void init() {
    userRepository = new FakeUserRepository();
  }


  @Test
  public void addUser() {
    val userId = UUID.randomUUID();

    UserRequestDTO requestDTO = new UserRequestDTO()
        .setId(userId);

    Boolean response = new AddUser(userRepository).execute(requestDTO);

    assertTrue(response);
    assertEquals(1, userRepository.users.size());
  }

}
