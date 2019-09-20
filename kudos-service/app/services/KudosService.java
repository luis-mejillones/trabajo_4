package services;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import model.Kudos;
import org.bson.Document;
import play.Logger;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

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
        kudos.id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        kudos.date = ZonedDateTime.now();
        Document doc = kudos.toDocument();
        this.collection.insertOne(doc);
        Logger.info("Kudos created with id: " + kudos.id);

        return kudos;
    }

    public List<Document> getAll() {
        MongoCursor<Document> cursor = this.collection.find().iterator();
        List<Document> list = this.retrieveDocuments(cursor);
        Logger.info("Kudos retrieved: " + list.size());

        return list;
    }

    public List<Document> getByTargetId(Integer id) {
        MongoCursor<Document> cursor = this.collection.find(eq("targetId", id)).iterator();
        List<Document> list = this.retrieveDocuments(cursor);
        Logger.info("Kudos retrieved: " + list.size() + " for user target id: " + id);

        return list;
    }

    public void delete(String id) {
        this.collection.deleteOne(eq("_id", id));
        Logger.info("Kudos delete with id: " + id);
    }

    private List<Document> retrieveDocuments(MongoCursor<Document> cursor) {
        List<Document> list = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                list.add(cursor.next());
            }
        } finally {
            cursor.close();
        }

        return list;
    }

    public void deleteByUserId(Integer userId) {
        Logger.info(">>> Deleting Kudos for userId: " + userId);
    }
}
