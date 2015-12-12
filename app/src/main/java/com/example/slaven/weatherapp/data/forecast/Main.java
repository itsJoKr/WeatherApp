
package com.example.slaven.weatherapp.data.forecast;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

public class Main implements Serializable {

    private double temp;
    private double temp_min;
    private double temp_max;
    private double pressure;
    private double sea_level;
    private double grnd_level;
    private int humidity;
    private int tempKf;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The temp
     */
    public double getTemp() {
        return temp;
    }

    /**
     * 
     * @param temp
     *     The temp
     */
    public void setTemp(double temp) {
        this.temp = temp;
    }

    /**
     * 
     * @return
     *     The temp_min
     */
    public double getTemp_min() {
        return temp_min;
    }

    /**
     * 
     * @param temp_min
     *     The temp_min
     */
    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    /**
     * 
     * @return
     *     The temp_max
     */
    public double getTemp_max() {
        return temp_max;
    }

    /**
     * 
     * @param temp_max
     *     The temp_max
     */
    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    /**
     * 
     * @return
     *     The pressure
     */
    public double getPressure() {
        return pressure;
    }

    /**
     * 
     * @param pressure
     *     The pressure
     */
    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    /**
     * 
     * @return
     *     The sea_level
     */
    public double getSea_level() {
        return sea_level;
    }

    /**
     * 
     * @param sea_level
     *     The sea_level
     */
    public void setSea_level(double sea_level) {
        this.sea_level = sea_level;
    }

    /**
     * 
     * @return
     *     The grnd_level
     */
    public double getGrnd_level() {
        return grnd_level;
    }

    /**
     * 
     * @param grnd_level
     *     The grnd_level
     */
    public void setGrnd_level(double grnd_level) {
        this.grnd_level = grnd_level;
    }

    /**
     * 
     * @return
     *     The humidity
     */
    public int getHumidity() {
        return humidity;
    }

    /**
     * 
     * @param humidity
     *     The humidity
     */
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    /**
     * 
     * @return
     *     The tempKf
     */
    public int getTempKf() {
        return tempKf;
    }

    /**
     * 
     * @param tempKf
     *     The temp_kf
     */
    public void setTempKf(int tempKf) {
        this.tempKf = tempKf;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
