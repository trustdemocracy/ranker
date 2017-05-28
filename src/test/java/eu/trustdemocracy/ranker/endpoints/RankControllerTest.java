package eu.trustdemocracy.ranker.endpoints;

import eu.trustdemocracy.ranker.core.models.request.LockRequestDTO;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class RankControllerTest extends ControllerTest {

  @Test
  public void addLockDate(TestContext context) {
    val async = context.async();

    val lock = new LockRequestDTO()
        .setTimestamp(System.currentTimeMillis());

    val single = client.post(port, HOST, "/locks/")
        .rxSendJson(lock);

    assert200(context, async, single);
  }

}
