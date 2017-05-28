package eu.trustdemocracy.ranker.core.interactors.relationship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import eu.trustdemocracy.ranker.core.models.request.RelationshipRequestDTO;
import eu.trustdemocracy.ranker.gateways.FakeRankRepository;
import java.util.UUID;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddRelationshipTest {

  private FakeRankRepository rankRepository;

  @BeforeEach
  public void init() {
    rankRepository = new FakeRankRepository();
  }


  @Test
  public void addRelationship() {
    val originId = UUID.randomUUID();
    val targetId = UUID.randomUUID();

    RelationshipRequestDTO requestDTO = new RelationshipRequestDTO()
        .setOriginId(originId)
        .setTargetId(targetId);

    Boolean response = new AddRelationship(rankRepository).execute(requestDTO);

    assertTrue(response);
    assertEquals(1, rankRepository.relationships.size());
    assertEquals(1, rankRepository.executionRequests.size());
  }
}
