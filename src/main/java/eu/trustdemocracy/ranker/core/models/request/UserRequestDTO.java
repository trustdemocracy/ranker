package eu.trustdemocracy.ranker.core.models.request;

import java.util.UUID;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserRequestDTO {

  private UUID id;
}
