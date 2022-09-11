package com.example.bli.caps;

import java.util.List;
import java.util.Map;

import ca.roumani.i2c.Country;
import ca.roumani.i2c.CountryDB;

public class Game {

    private CountryDB db;

    public Game()
    {
        this.db = new CountryDB();
    }

    public String qa()
    {
        List<String> capitals = db.getCapitals(); //Get a reference to the database's capital city list
        double n = capitals.size(); //Set n equal to size of Database's Capital City List
        System.out.println(n); //Size of Database's Capital City List

        int index =(int)(Math.random()*n); //Randomly Generate number in [0, capitals.size)
        String c = (String)capitals.get(index); //Set c equal to capital city of randomly generated value
        System.out.println(c); //Capital city randomly chosen

        Map<String, Country> data = db.getData(); //Get reference to the database's map
        System.out.println(data.size()); //Size of Database's Map List
        Country ref = (Country)data.get(c); //Invoke get(c) on the map to get a reference to a country

        System.out.println(ref.toString()); //Name of Country, It's Capital, and Population

        String result;
        if (Math.random() < 0.5) //If random number generated from [0, 1) is less tan 0.5
        {
            result = "What is the capital of " +ref.getName() +"? \n" +ref.getCapital(); //First Question Format
        }

        else //If random number generated from [0, 1) is more tan 0.5
        {
            result = ref.getCapital() +" is the capital of?" +"\n" +ref.getName(); //Second Question Format
        }
        return result; //Return question
    }

}
