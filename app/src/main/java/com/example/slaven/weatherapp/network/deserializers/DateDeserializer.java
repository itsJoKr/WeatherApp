package com.example.slaven.weatherapp.network.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Slaven on 25.7.2015..
 * Package name: com.example.slaven.weatherapp.util
 */
public class DateDeserializer implements JsonDeserializer<Date> {

    // Date comes in string, format example: 2015-07-25 21:00:00
    // Deserializing string into Date object

                                             //"2015-07-25 21:00:00"
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonPrimitive jsonPrimitive = (JsonPrimitive) json;

        String createdString = jsonPrimitive.getAsString();
        Date retDate = null;

        synchronized (sdf){
            try {
                retDate = sdf.parse(createdString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return retDate;
    }
}
