package eu.trustdemocracy.ranker.endpoints.controllers;

import eu.trustdemocracy.ranker.core.models.request.LockRequestDTO;
import eu.trustdemocracy.ranker.endpoints.App;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import lombok.val;

public class RankController extends Controller {

  public RankController(App app) {
    super(app);
  }

  @Override
  public void buildRoutes() {
    getRouter().post("/locks/").handler(this::addLockDate);
  }

  private void addLockDate(RoutingContext context) {
    try {
      val request = Json.decodeValue(context.getBodyAsString(), LockRequestDTO.class);
      val interactor = getInteractorFactory().getAddLockDate();

      val responseDTO = interactor.execute(request);

      serveJsonResponse(context, 200, Json.encodePrettily(responseDTO));
    } catch (Exception e) {
      serveBadRequest(context);
    }
  }

}
