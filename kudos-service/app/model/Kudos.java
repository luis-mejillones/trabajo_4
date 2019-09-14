package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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
}
