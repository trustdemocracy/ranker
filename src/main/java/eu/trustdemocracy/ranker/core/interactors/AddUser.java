package eu.trustdemocracy.ranker.core.interactors;

import eu.trustdemocracy.ranker.core.models.request.UserRequestDTO;
import eu.trustdemocracy.ranker.gateways.UserRepository;

public class AddUser implements Interactor<UserRequestDTO, Boolean> {

  public AddUser(UserRepository userRepository) {
  }

  @Override
  public Boolean execute(UserRequestDTO userRequestDTO) {
    return null;
  }
}
