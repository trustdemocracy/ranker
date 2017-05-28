package eu.trustdemocracy.ranker.core.interactors;

import eu.trustdemocracy.ranker.core.models.request.RelationshipRequestDTO;
import eu.trustdemocracy.ranker.gateways.RelationshipRepository;

public class AddRelationship implements Interactor<RelationshipRequestDTO, Boolean> {

  public AddRelationship(RelationshipRepository relationshipRepository) {
  }

  @Override
  public Boolean execute(RelationshipRequestDTO relationshipRequestDTO) {
    return null;
  }
}
