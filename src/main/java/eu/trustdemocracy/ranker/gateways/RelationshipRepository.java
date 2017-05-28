package eu.trustdemocracy.ranker.gateways;

import eu.trustdemocracy.ranker.core.entities.Relationship;

public interface RelationshipRepository {

  void create(Relationship relationship);

  void remove(Relationship relationship);
}
