package eu.trustdemocracy.ranker.endpoints.controllers;

import eu.trustdemocracy.ranker.endpoints.App;
import io.vertx.ext.web.RoutingContext;

public class UserController extends Controller {

  public UserController(App app) {
    super(app);
  }

  @Override
  public void buildRoutes() {
    getRouter().post("/users/").handler(this::addUser);
  }

  private void addUser(RoutingContext context) {

  }


}
