package eu.trustdemocracy.ranker.core.interactors;

import eu.trustdemocracy.ranker.core.entities.utils.UserMapper;
import eu.trustdemocracy.ranker.core.models.request.UserRequestDTO;
import eu.trustdemocracy.ranker.gateways.UserRepository;
import lombok.val;

public class RemoveUser implements Interactor<UserRequestDTO, Boolean> {

  private UserRepository userRepository;

  public RemoveUser(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Boolean execute(UserRequestDTO userRequestDTO) {
    val user = UserMapper.createEntity(userRequestDTO);
    userRepository.remove(user);
    return true;
  }
}
