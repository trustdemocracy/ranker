package eu.trustdemocracy.ranker.endpoints.controllers;

import eu.trustdemocracy.ranker.core.models.request.UserRequestDTO;
import eu.trustdemocracy.ranker.endpoints.App;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import java.util.UUID;
import lombok.val;

public class UserController extends Controller {

  public UserController(App app) {
    super(app);
  }

  @Override
  public void buildRoutes() {
    getRouter().post("/users/").handler(this::addUser);
    getRouter().delete("/users/:userId").handler(this::removeUser);
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

  private void removeUser(RoutingContext context) {
    try {
      val request = new UserRequestDTO()
          .setId(UUID.fromString(context.pathParam("userId")));
      val interactor = getInteractorFactory().getRemoveUser();

      val responseDTO = interactor.execute(request);

      serveJsonResponse(context, 200, Json.encodePrettily(responseDTO));
    } catch (Exception e) {
      serveBadRequest(context);
    }
  }


}
