package eu.trustdemocracy.ranker.core.interactors;

import eu.trustdemocracy.ranker.core.entities.Relationship;
import eu.trustdemocracy.ranker.core.entities.utils.RelationshipMapper;
import eu.trustdemocracy.ranker.core.models.request.RelationshipRequestDTO;
import eu.trustdemocracy.ranker.gateways.RelationshipRepository;

public class AddRelationship implements Interactor<RelationshipRequestDTO, Boolean> {

  private RelationshipRepository relationshipRepository;

  public AddRelationship(RelationshipRepository relationshipRepository) {
    this.relationshipRepository = relationshipRepository;
  }

  @Override
  public Boolean execute(RelationshipRequestDTO relationshipRequestDTO) {
    Relationship relationship = RelationshipMapper.createEntity(relationshipRequestDTO);
    relationshipRepository.create(relationship);
    return true;
  }
}
