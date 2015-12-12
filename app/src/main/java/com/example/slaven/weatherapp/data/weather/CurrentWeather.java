
package com.example.slaven.weatherapp.data.weather;

import com.example.slaven.weatherapp.data.forecast.Rain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class CurrentWeather {

    private com.example.slaven.weatherapp.data.forecast.Coord coord;
    private List<com.example.slaven.weatherapp.data.forecast.Weather> weather = new ArrayList<com.example.slaven.weatherapp.data.forecast.Weather>();
    private String base;
    private Main main;
    private com.example.slaven.weatherapp.data.forecast.Wind wind;
    private com.example.slaven.weatherapp.data.forecast.Clouds clouds;
    private com.example.slaven.weatherapp.data.forecast.Rain rain;
    private int dt;
    private int id;
    private String name;
    private int cod;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }
    /**
     * 
     * @return
     *     The coord
     */
    public com.example.slaven.weatherapp.data.forecast.Coord getCoord() {
        return coord;
    }

    /**
     * 
     * @param coord
     *     The coord
     */
    public void setCoord(com.example.slaven.weatherapp.data.forecast.Coord coord) {
        this.coord = coord;
    }

    /**
     * 
     * @return
     *     The weather
     */
    public List<com.example.slaven.weatherapp.data.forecast.Weather> getWeather() {
        return weather;
    }

    /**
     * 
     * @param weather
     *     The weather
     */
    public void setWeather(List<com.example.slaven.weatherapp.data.forecast.Weather> weather) {
        this.weather = weather;
    }

    /**
     * 
     * @return
     *     The base
     */
    public String getBase() {
        return base;
    }

    /**
     * 
     * @param base
     *     The base
     */
    public void setBase(String base) {
        this.base = base;
    }

    /**
     * 
     * @return
     *     The main
     */
    public Main getMain() {
        return main;
    }

    /**
     * 
     * @param main
     *     The main
     */
    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * 
     * @return
     *     The wind
     */
    public com.example.slaven.weatherapp.data.forecast.Wind getWind() {
        return wind;
    }

    /**
     * 
     * @param wind
     *     The wind
     */
    public void setWind(com.example.slaven.weatherapp.data.forecast.Wind wind) {
        this.wind = wind;
    }

    /**
     * 
     * @return
     *     The clouds
     */
    public com.example.slaven.weatherapp.data.forecast.Clouds getClouds() {
        return clouds;
    }

    /**
     * 
     * @param clouds
     *     The clouds
     */
    public void setClouds(com.example.slaven.weatherapp.data.forecast.Clouds clouds) {
        this.clouds = clouds;
    }

    /**
     * 
     * @return
     *     The dt
     */
    public int getDt() {
        return dt;
    }

    /**
     * 
     * @param dt
     *     The dt
     */
    public void setDt(int dt) {
        this.dt = dt;
    }

    /**
     * 
     * @return
     *     The id
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The cod
     */
    public int getCod() {
        return cod;
    }

    /**
     * 
     * @param cod
     *     The cod
     */
    public void setCod(int cod) {
        this.cod = cod;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
