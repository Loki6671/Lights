package com.project.task.models;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;

public class Room  implements Serializable {
    private int id;
    private String name;
    private String country;
    private  String light;
    public  Room(){}

    public Room(int id, String name, String country, String light) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.light = light;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String isLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", light=" + light +
                '}';
    }






}
