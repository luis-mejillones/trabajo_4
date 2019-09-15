package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.UserService;
import model.User;

import javax.inject.Inject;

public class UserController extends Controller {
    private final UserService service;

    @Inject
    public UserController(final UserService service) {
        this.service = service;
    }

    public Result create() throws Exception {
        JsonNode json = request().body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        }

        User user = Json.mapper().treeToValue(json, User.class);
        User out = this.service.create(user);

        JsonNode content = Json.toJson(out);
        return created(content);
    }

    public Result getAll() {
//        List<Document> list = this.service.getAll();
//        JsonNode content =  Json.toJson(list);
//
//        return ok(content);
        return ok();
    }

    public Result delete(String id) {
//        this.service.delete(id);

        return ok();
    }
}
