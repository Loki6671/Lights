package com.project.task.dao;

import com.project.task.models.Country;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class DaoCountry {
    private ArrayList<Country> countryList= new ArrayList<>();
    {
        countryList.add(new Country("Kazakhstan"));
        countryList.add(new Country("Belarus"));
        countryList.add(new Country("Russia"));
        countryList.add(new Country("India"));
        countryList.add(new Country("China"));
        countryList.add(new Country("Japan"));

    }
    public  ArrayList<Country> showCountry(){
        return  countryList;
    }

}
