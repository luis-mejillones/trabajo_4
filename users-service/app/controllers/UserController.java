package controllers;

import akka.japi.Pair;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.Kudos;
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
        User user = this.service.getById(id);
        JsonNode content = Json.toJson(user);

        return ok(content);
    }

    public Result getDetailById(Integer id) {
        Pair<User, List<Kudos>> pair = this.service.getDetailById(id);
        JsonNode user = Json.toJson(pair.first());
        JsonNode kudos = Json.toJson(pair.second());

        ((ObjectNode)user).set("kudos", kudos);

        return ok(user);
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

    public Result testmq() throws Exception {
//        String QUEUE_NAME = "hello";
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        factory.setPort(5672);
//        factory.setUsername("admin");
//        factory.setPassword("12345");
//        try (Connection connection = factory.newConnection();
//             Channel channel = connection.createChannel()) {
//            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//            String message = "Hello World!";
//            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
//            System.out.println(" [x] Sent '" + message + "'");
//        }
//        this.service.sendMessage("NEW MSG :) ");
        return ok(" [x] Sent ''");
    }
}
