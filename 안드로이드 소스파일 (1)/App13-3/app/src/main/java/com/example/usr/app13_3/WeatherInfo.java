package com.example.usr.app13_3;

public class WeatherInfo {
    private String name;
    private Coord coord;
    private Main main;

    public WeatherInfo(){}
    public WeatherInfo(String name, Coord coord, Main main) {
        this.name = name;
        this.coord = coord;
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "name='" + name + '\'' +
                ", coord=" + coord +
                ", main=" + main +
                '}';
    }
}
