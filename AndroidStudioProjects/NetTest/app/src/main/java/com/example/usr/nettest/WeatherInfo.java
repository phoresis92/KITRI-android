package com.example.usr.nettest;

/**
 * Created by usr on 2015-08-28.
 */
public class WeatherInfo {
    private String city;
    private String temperature;
    private String humidity;
    private String clouds;

    public WeatherInfo(){}
    public WeatherInfo(String city, String temperature, String humidity, String clouds) {
        this.city = city;
        this.temperature = temperature;
        this.humidity = humidity;
        this.clouds = clouds;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "city='" + city + '\'' +
                ", temperature='" + temperature + '\'' +
                ", humidity='" + humidity + '\'' +
                ", clouds='" + clouds + '\'' +
                '}';
    }
}
