package eu.trustdemocracy.ranker.gateways;

import eu.trustdemocracy.ranker.core.entities.Relationship;
import java.util.HashSet;
import java.util.Set;

public class FakeRelationshipRepository implements RelationshipRepository {

  public Set<Relationship> relationships = new HashSet<>();

  @Override
  public void create(Relationship relationship) {
    relationships.add(relationship);
  }

  @Override
  public void remove(Relationship relationship) {
    relationships.remove(relationship);
  }
}
