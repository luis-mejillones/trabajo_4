package services;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import model.Kudos;
import org.bson.Document;
import play.Logger;
import util.Constants;
import util.message.Message;
import util.message.MessageType;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class KudosService {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    private final KudosMessageSender kudosMessageSender;

    @Inject
    public KudosService(KudosMessageSender kudosMessageSender) {
        this.kudosMessageSender = kudosMessageSender;
        this.mongoClient = new MongoClient(Constants.MONGODB_HOST , Constants.MONGODB_PORT );
        this.database = this.mongoClient.getDatabase(Constants.MONGODB_DATABASE);
        this.collection = this.database.getCollection(Constants.MONGODB_COLLECTION);
    }

    public void close() {
        this.mongoClient.close();
    }

    public Kudos create(Kudos kudos) {
        kudos.id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        kudos.date = ZonedDateTime.now();
        Document doc = kudos.toDocument();
        this.collection.insertOne(doc);
        Logger.info(">>> Kudos created with id: " + kudos.id);
//        this.sendMessage(MessageType.NEW_KUDOS,kudos.toString());

        return kudos;
    }

    public List<Document> getAll() {
        MongoCursor<Document> cursor = this.collection.find().iterator();
        List<Document> list = this.retrieveDocuments(cursor);
        Logger.info(">>> Kudos retrieved: " + list.size());

        return list;
    }

    public List<Document> getByTargetId(Integer id) {
        MongoCursor<Document> cursor = this.collection.find(eq("targetId", id)).iterator();
        List<Document> list = this.retrieveDocuments(cursor);
        Logger.info(">>> Kudos retrieved: " + list.size() + " for user target id: " + id);

        return list;
    }

    public void delete(String id) {
        this.collection.deleteOne(eq("_id", id));
        Logger.info(">>> Kudos delete with id: " + id);
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
        DeleteResult result = this.collection.deleteMany(eq("targetId", userId));
        Logger.info(">>> Deleting target Kudos for userId: " + userId + " Result: " + result.toString());

        result = this.collection.deleteMany(eq("sourceId", userId));
        Logger.info(">>> Deleting source Kudos for userId: " + userId + " Result: " + result.toString());
    }

    public void sendMessage(MessageType type, String message) {
        Message msg = new Message();
        msg.setMessageType(type);
        msg.setContent(message);

        this.kudosMessageSender.send(msg);
    }
}
