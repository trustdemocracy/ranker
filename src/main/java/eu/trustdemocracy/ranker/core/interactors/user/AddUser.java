package eu.trustdemocracy.ranker.core.interactors.user;

import eu.trustdemocracy.ranker.core.entities.User;
import eu.trustdemocracy.ranker.core.entities.utils.UserMapper;
import eu.trustdemocracy.ranker.core.interactors.Interactor;
import eu.trustdemocracy.ranker.core.models.request.UserRequestDTO;
import eu.trustdemocracy.ranker.gateways.repositories.RankRepository;

public class AddUser implements Interactor<UserRequestDTO, Boolean> {

  private RankRepository rankRepository;

  public AddUser(RankRepository rankRepository) {
    this.rankRepository = rankRepository;
  }

  @Override
  public Boolean execute(UserRequestDTO userRequestDTO) {
    User user = UserMapper.createEntity(userRequestDTO);
    rankRepository.createUser(user);
    rankRepository.enqueueRequest(System.currentTimeMillis());
    return true;
  }
}
