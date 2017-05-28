package eu.trustdemocracy.ranker.gateways.out;

import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.ext.web.client.WebClient;
import java.util.Map;
import java.util.UUID;
import lombok.val;

public class UsersGatewayImpl implements UsersGateway {

  private Vertx vertx = Vertx.vertx();
  private static String host;
  private static Integer port;

  @Override
  public void updateRank(Map<UUID, Double> results, long lastCalculatedTime) {
    val json = new JsonObject()
        .put("calculatedTime", lastCalculatedTime)
        .put("rankings", results);

    WebClient.create(vertx)
        .post(getUsersPort(), getUsersHost(), "/rank")
        .rxSendJson(json)
        .subscribe();
  }

  private static String getUsersHost() {
    if (host == null) {
      host = System.getenv("users_host");
    }
    return host;
  }

  private static int getUsersPort() {
    if (port == null) {
      port = Integer.valueOf(System.getenv("users_port"));
    }
    return port;
  }
}
