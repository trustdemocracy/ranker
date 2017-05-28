package eu.trustdemocracy.ranker.core.entities.utils;

import eu.trustdemocracy.ranker.core.entities.Relationship;
import eu.trustdemocracy.ranker.core.models.request.RelationshipRequestDTO;

public class RelationshipMapper {

  public static Relationship createEntity(RelationshipRequestDTO relationshipRequestDTO) {
    return new Relationship()
        .setOriginId(relationshipRequestDTO.getOriginId())
        .setTargetId(relationshipRequestDTO.getTargetId());
  }
}
