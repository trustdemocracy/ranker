package eu.trustdemocracy.ranker.endpoints;

import eu.trustdemocracy.ranker.core.models.request.RelationshipRequestDTO;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import java.util.UUID;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class RelationshipControllerTest extends ControllerTest {

  @Test
  public void addRelationship(TestContext context) {
    val async = context.async();

    val relationship = new RelationshipRequestDTO()
        .setOriginId(UUID.randomUUID())
        .setTargetId(UUID.randomUUID());

    val single = client.post(port, HOST, "/relationships/")
        .rxSendJson(relationship);

    assert200(context, async, single);
  }

  @Test
  public void removeRelationship(TestContext context) {
    val async = context.async();

    val relationship = new RelationshipRequestDTO()
        .setOriginId(UUID.randomUUID())
        .setTargetId(UUID.randomUUID());

    client.post(port, HOST, "/relationships/")
        .rxSendJson(relationship)
        .subscribe(response -> {
          val single = client.delete(port, HOST, "/relationships/remove")
              .rxSendJson(relationship);
          assert200(context, async, single);
        });

  }


}
