package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import model.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.UserService;

import javax.inject.Inject;
import java.util.List;

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
        List<User> list = this.service.getAll();
        JsonNode content =  Json.toJson(list);

        return ok(content);
    }

    public Result getById(Integer id) {
        User out = this.service.getById(id);

        JsonNode content = Json.toJson(out);
        return created(content);
    }

    public Result delete(Integer id) {
        this.service.delete(id);

        return ok();
    }

    public Result find() {
        String nickname = request().getQueryString("nickname");
        String name = request().getQueryString("name");
        List<User> list = this.service.find(nickname, name);

        JsonNode content =  Json.toJson(list);

        return ok(content);
    }
}
