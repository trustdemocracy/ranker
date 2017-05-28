package eu.trustdemocracy.ranker.core.interactors.relationship;

import eu.trustdemocracy.ranker.core.entities.Relationship;
import eu.trustdemocracy.ranker.core.entities.utils.RelationshipMapper;
import eu.trustdemocracy.ranker.core.interactors.Interactor;
import eu.trustdemocracy.ranker.core.models.request.RelationshipRequestDTO;
import eu.trustdemocracy.ranker.gateways.repositories.RankRepository;

public class AddRelationship implements Interactor<RelationshipRequestDTO, Boolean> {

  private RankRepository rankRepository;

  public AddRelationship(RankRepository rankRepository) {
    this.rankRepository = rankRepository;
  }

  @Override
  public Boolean execute(RelationshipRequestDTO relationshipRequestDTO) {
    Relationship relationship = RelationshipMapper.createEntity(relationshipRequestDTO);
    rankRepository.createRelationship(relationship);
    rankRepository.addExecutionRequest(System.currentTimeMillis());
    return true;
  }
}
