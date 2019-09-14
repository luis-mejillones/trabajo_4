package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Kudos;
import org.bson.Document;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.KudosService;

import javax.inject.Inject;
import java.util.UUID;

public class KudosController extends Controller {
    private final KudosService service;

    @Inject
    public KudosController(final KudosService service) {
        this.service = service;
    }

    public Result create() throws Exception {
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongoClient.getDatabase("omega");
        MongoCollection<Document> collection = database.getCollection("kudos");

        JsonNode json = request().body().asJson();

        if (json == null){
            return badRequest("Expecting Json data");
        }

        String baseHref = "http://localhost:9001/kudos";
        Kudos kudos = Json.mapper().treeToValue(json, Kudos.class);
        kudos.id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        kudos.href = String.format("%s/%s", baseHref, kudos.id);
        JsonNode content = Json.toJson(kudos);

        Document doc = new Document("_id", kudos.id)
                .append("href", kudos.href)
                .append("topic", kudos.topic)
                .append("date", kudos.date.toString())
                .append("place", kudos.place)
                .append("targetId", kudos.targetId)
                .append("content", kudos.content)
                .append("sourceId", kudos.sourceId);

        collection.insertOne(doc);
        mongoClient.close();

        return created(content);
    }
}
