package services;

import model.User;
import play.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static util.Constants.BASE_HREF;

public class UserService {

    public UserService() {
    }

    public void close() {

    }

    public User create(User user) {
        String baseHref = BASE_HREF + "/kudos";
        user.id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        user.href = String.format("%s/%s", baseHref, user.id);

//        Document doc = kudos.toDocument();
//        this.collection.insertOne(doc);
//        Logger.info("Kudos created with id: " + kudos);

        return user;
    }

    public List<User> getAll() {
//        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
//        MongoDatabase database = mongoClient.getDatabase("omega");
//        MongoCollection<Document> collection = database.getCollection("kudos");
//
//        MongoCursor<Document> cursor = collection.find().iterator();
//        List<Document> list = new ArrayList<>();
//        try {
//            while (cursor.hasNext()) {
//                list.add(cursor.next());
//            }
//        } finally {
//            cursor.close();
//        }

        Logger.info("Kudos retrieved: " + 0);

        return new ArrayList<>();
    }

    public void delete(String id) {
//        this.collection.deleteOne(eq("_id", id));
        Logger.info("Kudos delete with id: " + id);
    }
}