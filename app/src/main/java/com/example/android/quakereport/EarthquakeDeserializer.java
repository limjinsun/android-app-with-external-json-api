package com.example.android.quakereport;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class EarthquakeDeserializer extends JsonDeserializer<Earthquake> {

    @Override
    public Earthquake deserialize(JsonParser parser, DeserializationContext deserializerContext) throws IOException {

        Earthquake earthquake = new Earthquake();
//
//        JsonNode productNode = parser.getCodec().readTree(parser);
//        earthquake.setmMagnitude(productNode.get("properties").get("mag").asDouble());
//        earthquake.setmLocation(productNode.get("properties").get("place").textValue());
//        earthquake.setmDate(productNode.get("properties").get("time").asLong());

        return earthquake;

    }
}
