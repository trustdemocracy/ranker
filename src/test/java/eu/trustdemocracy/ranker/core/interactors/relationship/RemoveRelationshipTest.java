package eu.trustdemocracy.ranker.core.interactors.relationship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import eu.trustdemocracy.ranker.core.models.request.RelationshipRequestDTO;
import eu.trustdemocracy.ranker.gateways.FakeRankRepository;
import java.util.UUID;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RemoveRelationshipTest {

  private FakeRankRepository rankRepository;

  @BeforeEach
  public void init() {
    rankRepository = new FakeRankRepository();
  }


  @Test
  public void removeRelationship() {
    val originId = UUID.randomUUID();
    val targetId = UUID.randomUUID();

    val requestDTO = new RelationshipRequestDTO()
        .setOriginId(originId)
        .setTargetId(targetId);

    new AddRelationship(rankRepository).execute(requestDTO);

    assertEquals(1, rankRepository.relationships.size());

    val deleteRequestDTO = new RelationshipRequestDTO()
        .setOriginId(originId)
        .setTargetId(targetId);

    Boolean response = new RemoveRelationship(rankRepository).execute(deleteRequestDTO);

    assertTrue(response);
    assertEquals(0, rankRepository.relationships.size());
  }
}
