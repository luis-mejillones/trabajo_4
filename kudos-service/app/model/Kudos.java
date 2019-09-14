package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.Document;

import java.time.ZonedDateTime;

import static util.Constants.DATE_FORMAT;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Kudos {
    @JsonProperty
    public String id;

    @JsonProperty
    public String href;

    @JsonProperty
    public String topic;

    @JsonFormat(pattern = DATE_FORMAT, timezone = "UTC")
    @JsonProperty
    public ZonedDateTime date;

    @JsonProperty
    public String place;

    @JsonProperty
    public String targetId;

    @JsonProperty
    public String content;

    @JsonProperty
    public String sourceId;

    public Document toDocument() {
        Document doc = new Document("_id", this.id)
                .append("href", this.href)
                .append("topic", this.topic)
                .append("date", this.date.toString())
                .append("place", this.place)
                .append("targetId", this.targetId)
                .append("content", this.content)
                .append("sourceId", this.sourceId);

        return doc;
    }

    public void fromDocument(Document doc) {
        this.id = doc.getString("_id");
        this.href = doc.getString("href");
        this.topic = doc.getString("topic");
        this.date = ZonedDateTime.parse(doc.getString("date"));
        this.place = doc.getString("place");
        this.targetId = doc.getString("targetId");
        this.content = doc.getString("content");
        this.sourceId = doc.getString("sourceId");
    }
}
