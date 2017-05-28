package eu.trustdemocracy.ranker.core.interactors;

import eu.trustdemocracy.ranker.core.models.request.UserRequestDTO;
import eu.trustdemocracy.ranker.gateways.UserRepository;

public class RemoveUser implements Interactor<UserRequestDTO, Boolean> {

  public RemoveUser(UserRepository userRepository) {
  }

  @Override
  public Boolean execute(UserRequestDTO userRequestDTO) {
    return null;
  }
}
