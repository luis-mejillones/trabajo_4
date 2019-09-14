package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import model.Kudos;
import org.bson.Document;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.KudosService;

import javax.inject.Inject;
import java.util.List;

public class KudosController extends Controller {
    private final KudosService service;

    @Inject
    public KudosController(final KudosService service) {
        this.service = service;
    }

    public Result create() throws Exception {
        JsonNode json = request().body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        }

        Kudos kudos = Json.mapper().treeToValue(json, Kudos.class);
        Kudos out = this.service.create(kudos);

        JsonNode content = Json.toJson(out);
        return created(content);
    }

    public Result getAll() {
        List<Document> list = this.service.getAll();
        JsonNode content =  Json.toJson(list);

        return ok(content);
    }

    public Result delete(String id) {
        this.service.delete(id);

        return ok();
    }
}
