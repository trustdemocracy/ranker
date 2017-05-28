package eu.trustdemocracy.ranker.endpoints;

import eu.trustdemocracy.ranker.core.models.request.UserRequestDTO;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import java.util.UUID;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class UserControllerTest extends ControllerTest {

  @Test
  public void addUser(TestContext context) {
    val async = context.async();

    val user = new UserRequestDTO()
        .setId(UUID.randomUUID());

    val single = client.post(port, HOST, "/users/")
        .rxSendJson(user);

    assert200(context, async, single);
  }

}
