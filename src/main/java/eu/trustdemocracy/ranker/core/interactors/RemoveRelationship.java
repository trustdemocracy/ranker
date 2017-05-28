package eu.trustdemocracy.ranker.core.interactors;

import eu.trustdemocracy.ranker.core.models.request.RelationshipRequestDTO;
import eu.trustdemocracy.ranker.gateways.RelationshipRepository;

public class RemoveRelationship implements Interactor<RelationshipRequestDTO, Boolean> {

  public RemoveRelationship(RelationshipRepository relationshipRepository) {
  }

  @Override
  public Boolean execute(RelationshipRequestDTO relationshipRequestDTO) {
    return null;
  }
}
