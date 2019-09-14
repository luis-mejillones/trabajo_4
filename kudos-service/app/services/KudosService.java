package services;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import model.Kudos;
import org.bson.Document;
import play.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;
import static util.Constants.BASE_HREF;

//import util.Logger;

public class KudosService {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public KudosService() {
        this.mongoClient = new MongoClient( "localhost" , 27017 );
        this.database = this.mongoClient.getDatabase("omega");
        this.collection = this.database.getCollection("kudos");
    }

    public void close() {
        this.mongoClient.close();
    }

    public Kudos create(Kudos kudos) {
        String baseHref = BASE_HREF + "/kudos";
        kudos.id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        kudos.href = String.format("%s/%s", baseHref, kudos.id);

        Document doc = kudos.toDocument();
        this.collection.insertOne(doc);
        Logger.info("Kudos created with id: " + kudos);

        return kudos;
    }

    public List<Document> getAll() {
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongoClient.getDatabase("omega");
        MongoCollection<Document> collection = database.getCollection("kudos");

        MongoCursor<Document> cursor = collection.find().iterator();
        List<Document> list = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                list.add(cursor.next());
            }
        } finally {
            cursor.close();
        }

        Logger.info("Kudos retrieved: " + list.size());

        return list;
    }

    public void delete(String id) {
        this.collection.deleteOne(eq("_id", id));
        Logger.info("Kudos delete with id: " + id);
    }
}
