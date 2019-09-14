package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import services.UserService;

import javax.inject.Inject;

public class UserController extends Controller {
    private final UserService service;

    @Inject
    public UserController(final UserService service) {
        this.service = service;
    }

    public Result create() throws Exception {
//        JsonNode json = request().body().asJson();
//        if (json == null) {
//            return badRequest("Expecting Json data");
//        }
//
//        Kudos kudos = Json.mapper().treeToValue(json, Kudos.class);
//        Kudos out = this.service.create(kudos);
//
//        JsonNode content = Json.toJson(out);
//        return created(content);
        return ok();
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
