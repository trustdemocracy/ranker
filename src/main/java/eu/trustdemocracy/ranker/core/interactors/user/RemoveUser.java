package eu.trustdemocracy.ranker.core.interactors.user;

import eu.trustdemocracy.ranker.core.entities.utils.UserMapper;
import eu.trustdemocracy.ranker.core.interactors.Interactor;
import eu.trustdemocracy.ranker.core.models.request.UserRequestDTO;
import eu.trustdemocracy.ranker.gateways.RankRepository;
import lombok.val;

public class RemoveUser implements Interactor<UserRequestDTO, Boolean> {

  private RankRepository rankRepository;

  public RemoveUser(RankRepository rankRepository) {
    this.rankRepository = rankRepository;
  }

  @Override
  public Boolean execute(UserRequestDTO userRequestDTO) {
    val user = UserMapper.createEntity(userRequestDTO);
    rankRepository.removeUser(user);
    return true;
  }
}
