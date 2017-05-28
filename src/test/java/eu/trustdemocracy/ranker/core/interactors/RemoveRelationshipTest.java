package eu.trustdemocracy.ranker.core.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import eu.trustdemocracy.ranker.core.models.request.RelationshipRequestDTO;
import eu.trustdemocracy.ranker.gateways.FakeRelationshipRepository;
import java.util.UUID;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RemoveRelationshipTest {

  private FakeRelationshipRepository relationshipRepository;

  @BeforeEach
  public void init() {
    relationshipRepository = new FakeRelationshipRepository();
  }


  @Test
  public void removeRelationship() {
    val originId = UUID.randomUUID();
    val targetId = UUID.randomUUID();

    val requestDTO = new RelationshipRequestDTO()
        .setOriginId(originId)
        .setTargetId(targetId);

    new AddRelationship(relationshipRepository).execute(requestDTO);

    assertEquals(1, relationshipRepository.relationships.size());

    val deleteRequestDTO = new RelationshipRequestDTO()
        .setOriginId(originId)
        .setTargetId(targetId);

    Boolean response = new RemoveRelationship(relationshipRepository).execute(deleteRequestDTO);

    assertTrue(response);
    assertEquals(0, relationshipRepository.relationships.size());
  }
}
