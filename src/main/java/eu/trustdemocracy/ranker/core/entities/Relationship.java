package eu.trustdemocracy.ranker.core.entities;

import java.util.UUID;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Relationship {

  private UUID originId;
  private UUID targetId;
}
