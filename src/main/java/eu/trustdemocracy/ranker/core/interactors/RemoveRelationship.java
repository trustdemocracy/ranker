package eu.trustdemocracy.ranker.core.interactors;

import eu.trustdemocracy.ranker.core.entities.utils.RelationshipMapper;
import eu.trustdemocracy.ranker.core.models.request.RelationshipRequestDTO;
import eu.trustdemocracy.ranker.gateways.RelationshipRepository;
import lombok.val;

public class RemoveRelationship implements Interactor<RelationshipRequestDTO, Boolean> {

  private RelationshipRepository relationshipRepository;

  public RemoveRelationship(RelationshipRepository relationshipRepository) {
    this.relationshipRepository = relationshipRepository;
  }

  @Override
  public Boolean execute(RelationshipRequestDTO relationshipRequestDTO) {
    val relationship = RelationshipMapper.createEntity(relationshipRequestDTO);
    relationshipRepository.remove(relationship);
    return true;
  }
}
