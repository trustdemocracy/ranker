package eu.trustdemocracy.ranker.core.entities.utils;

import eu.trustdemocracy.ranker.core.entities.User;
import eu.trustdemocracy.ranker.core.models.request.UserRequestDTO;

public class UserMapper {

  public static User createEntity(UserRequestDTO userRequestDTO) {
    return new User()
        .setId(userRequestDTO.getId());
  }
}
