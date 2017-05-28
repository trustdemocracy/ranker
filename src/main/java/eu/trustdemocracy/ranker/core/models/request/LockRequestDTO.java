package eu.trustdemocracy.ranker.core.models.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LockRequestDTO {

  private long timestamp;
}
