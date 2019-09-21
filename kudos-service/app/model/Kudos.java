package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.bson.Document;
import play.libs.Json;

import java.time.ZonedDateTime;

import static util.Constants.DATE_FORMAT;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Kudos {
    public Kudos() {
        this.messaged = Boolean.FALSE;
    }

    @JsonProperty("_id")
    public String id;

    @JsonProperty
    public String topic;

    @JsonFormat(pattern = DATE_FORMAT, timezone = "UTC")
    @JsonProperty
    public ZonedDateTime date;

    @JsonProperty
    public String place;

    @JsonProperty
    public String content;

    @JsonProperty
    public Integer targetId;

    @JsonProperty
    public Integer sourceId;

    @JsonProperty
    public Boolean messaged;

    @JsonIgnore
    public Document toDocument() {
        Document doc = new Document("_id", this.id)
                .append("topic", this.topic)
                .append("date", this.date.toString())
                .append("place", this.place)
                .append("content", this.content)
                .append("targetId", this.targetId)
                .append("sourceId", this.sourceId)
                .append("messaged", this.messaged);

        return doc;
    }

    @JsonIgnore
    public void fromDocument(Document doc) {
        this.id = doc.getString("_id");
        this.topic = doc.getString("topic");
        this.date = ZonedDateTime.parse(doc.getString("date"));
        this.place = doc.getString("place");
        this.content = doc.getString("content");
        this.targetId = doc.getInteger("targetId");
        this.sourceId = doc.getInteger("sourceId");
        this.messaged = doc.getBoolean("messaged");
    }

    @JsonIgnore
    public String toString() {
        JsonNode content =  Json.toJson(this);

        return content.toString();
    }
}
