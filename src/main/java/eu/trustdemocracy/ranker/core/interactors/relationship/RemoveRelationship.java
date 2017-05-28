package eu.trustdemocracy.ranker.core.interactors.relationship;

import eu.trustdemocracy.ranker.core.entities.utils.RelationshipMapper;
import eu.trustdemocracy.ranker.core.interactors.Interactor;
import eu.trustdemocracy.ranker.core.models.request.RelationshipRequestDTO;
import eu.trustdemocracy.ranker.gateways.repositories.RankRepository;
import lombok.val;

public class RemoveRelationship implements Interactor<RelationshipRequestDTO, Boolean> {

  private RankRepository rankRepository;

  public RemoveRelationship(RankRepository rankRepository) {
    this.rankRepository = rankRepository;
  }

  @Override
  public Boolean execute(RelationshipRequestDTO relationshipRequestDTO) {
    val relationship = RelationshipMapper.createEntity(relationshipRequestDTO);
    rankRepository.removeRelationship(relationship);
    rankRepository.addExecutionRequest(System.currentTimeMillis());
    return true;
  }
}
