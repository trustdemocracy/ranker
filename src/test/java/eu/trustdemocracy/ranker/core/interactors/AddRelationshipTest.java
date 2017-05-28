package eu.trustdemocracy.ranker.core.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import eu.trustdemocracy.ranker.core.models.request.RelationshipRequestDTO;
import eu.trustdemocracy.ranker.gateways.FakeRelationshipRepository;
import java.util.UUID;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddRelationshipTest {

  private FakeRelationshipRepository relationshipRepository;

  @BeforeEach
  public void init() {
    relationshipRepository = new FakeRelationshipRepository();
  }


  @Test
  public void addRelationship() {
    val originId = UUID.randomUUID();
    val targetId = UUID.randomUUID();

    RelationshipRequestDTO requestDTO = new RelationshipRequestDTO()
        .setOriginId(originId)
        .setTargetId(targetId);

    Boolean response = new AddRelationship(relationshipRepository).execute(requestDTO);

    assertTrue(response);
    assertEquals(1, relationshipRepository.relationships.size());
  }
}
