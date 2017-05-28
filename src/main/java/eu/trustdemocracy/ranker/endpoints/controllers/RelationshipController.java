package eu.trustdemocracy.ranker.endpoints.controllers;

import eu.trustdemocracy.ranker.core.models.request.RelationshipRequestDTO;
import eu.trustdemocracy.ranker.endpoints.App;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import lombok.val;

public class RelationshipController extends Controller {

  public RelationshipController(App app) {
    super(app);
  }

  @Override
  public void buildRoutes() {
    getRouter().post("/relationships/").handler(this::addRelationship);
    getRouter().post("/relationships/remove").handler(this::removeRelationship);
  }

  private void addRelationship(RoutingContext context) {
    try {
      val request = Json.decodeValue(context.getBodyAsString(), RelationshipRequestDTO.class);
      val interactor = getInteractorFactory().getAddRelationship();

      val responseDTO = interactor.execute(request);

      serveJsonResponse(context, 200, Json.encodePrettily(responseDTO));
    } catch (Exception e) {
      serveBadRequest(context);
    }
  }

  private void removeRelationship(RoutingContext context) {
    try {
      val request = Json.decodeValue(context.getBodyAsString(), RelationshipRequestDTO.class);
      val interactor = getInteractorFactory().getRemoveRelationship();

      val responseDTO = interactor.execute(request);

      serveJsonResponse(context, 200, Json.encodePrettily(responseDTO));
    } catch (Exception e) {
      serveBadRequest(context);
    }
  }


}
