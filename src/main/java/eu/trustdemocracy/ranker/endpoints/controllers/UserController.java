package eu.trustdemocracy.ranker.endpoints.controllers;

import eu.trustdemocracy.ranker.core.models.request.UserRequestDTO;
import eu.trustdemocracy.ranker.endpoints.App;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import lombok.val;

public class UserController extends Controller {

  public UserController(App app) {
    super(app);
  }

  @Override
  public void buildRoutes() {
    getRouter().post("/users/").handler(this::addUser);
  }

  private void addUser(RoutingContext context) {
    try {
      val request = Json.decodeValue(context.getBodyAsString(), UserRequestDTO.class);
      val interactor = getInteractorFactory().getAddUser();

      val responseDTO = interactor.execute(request);

      serveJsonResponse(context, 200, Json.encodePrettily(responseDTO));
    } catch (Exception e) {
      serveBadRequest(context);
    }
  }


}
