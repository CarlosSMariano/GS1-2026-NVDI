package br.com.nvdi.gs.application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosAtuais {
    private String time;
    private double temperature_2m;
    private int relative_humidity_2m;
    private double wind_speed_10m;
    private double rain;
    private double surface_pressure;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getTemperature_2m() {
        return temperature_2m;
    }

    public void setTemperature_2m(double temperature_2m) {
        this.temperature_2m = temperature_2m;
    }

    public int getRelative_humidity_2m() {
        return relative_humidity_2m;
    }

    public void setRelative_humidity_2m(int relative_humidity_2m) {
        this.relative_humidity_2m = relative_humidity_2m;
    }

    public double getWind_speed_10m() {
        return wind_speed_10m;
    }

    public void setWind_speed_10m(double wind_speed_10m) {
        this.wind_speed_10m = wind_speed_10m;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    public double getSurface_pressure() {
        return surface_pressure;
    }

    public void setSurface_pressure(double surface_pressure) {
        this.surface_pressure = surface_pressure;
    }

    @Override
    public String toString() {
        return "DadosAtuais{" + "time=" + time  + ", temperature_2m=" + temperature_2m
                + ", relative_humidity_2m=" + relative_humidity_2m
                + ", wind_speed_10m=" + wind_speed_10m + ", rain=" + rain
                + ", surface_pressure=" + surface_pressure + '}';
    }
}
