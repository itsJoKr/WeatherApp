package com.example.slaven.weatherapp.network.deserializers;

import com.example.slaven.weatherapp.util.Condition;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

import java.lang.reflect.Type;

/**
 * Created by Slaven on 26.7.2015..
 * Package name: com.example.slaven.weatherapp.util
 */
public class ConditionDeserializer implements JsonDeserializer<Condition> {
    // Condition comes in id that represents weather condition

    @Override
    public Condition deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonPrimitive jsonPrimitive = (JsonPrimitive) json;

        int conditionId = jsonPrimitive.getAsInt();

        return Condition.getConditionFromId(conditionId);
    }
}
