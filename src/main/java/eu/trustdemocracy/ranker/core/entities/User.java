package eu.trustdemocracy.ranker.core.entities;

import java.util.Set;
import java.util.UUID;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {

  private UUID id;
  private Double rank;

  private int outRelationshipsCount;
  private Set<UUID> inRelationships;
}
