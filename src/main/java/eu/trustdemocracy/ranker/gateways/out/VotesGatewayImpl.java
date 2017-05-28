package eu.trustdemocracy.ranker.gateways.out;

import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.ext.web.client.WebClient;
import java.util.Map;
import java.util.UUID;
import lombok.val;

public class VotesGatewayImpl implements VotesGateway {

  private Vertx vertx = Vertx.vertx();
  private static String host;
  private static Integer port;

  @Override
  public void updateRank(Map<UUID, Double> results, long lastCalculatedTime) {
    val json = new JsonObject()
        .put("calculatedTime", lastCalculatedTime)
        .put("rankings", results);

    WebClient.create(vertx)
        .post(getVotesPort(), getVotesHost(), "/rank")
        .rxSendJson(json)
        .subscribe();
  }

  private static String getVotesHost() {
    if (host == null) {
      host = System.getenv("votes_host");
    }
    return host;
  }

  private static int getVotesPort() {
    if (port == null) {
      port = Integer.valueOf(System.getenv("votes_port"));
    }
    return port;
  }
}
