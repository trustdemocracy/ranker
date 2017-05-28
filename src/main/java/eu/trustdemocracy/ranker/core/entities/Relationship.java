package eu.trustdemocracy.ranker.core.entities;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Relationship {

  private String originId;
  private String targetId;
}
