
package com.example.slaven.weatherapp.data.forecast;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
public class Rain implements Serializable {

    @SerializedName("3h")
    private double rainPer3h;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public double getRainPer3h() {
        return rainPer3h;
    }

    public void setRainPer3h(double rainPer3h) {
        this.rainPer3h = rainPer3h;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
