package eu.trustdemocracy.ranker.core.interactors;

import eu.trustdemocracy.ranker.core.entities.User;
import eu.trustdemocracy.ranker.core.entities.utils.UserMapper;
import eu.trustdemocracy.ranker.core.models.request.UserRequestDTO;
import eu.trustdemocracy.ranker.gateways.UserRepository;

public class AddUser implements Interactor<UserRequestDTO, Boolean> {

  private UserRepository userRepository;

  public AddUser(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Boolean execute(UserRequestDTO userRequestDTO) {
    User user = UserMapper.createEntity(userRequestDTO);
    userRepository.create(user);
    return true;
  }
}
