package com.example.spaceapp.spaceitems;

public class Building {
    private String type;
    private Planet planet;
    private int cost;
    private int level;

    public Building(Planet myPlanet, String buildingType){
        this.planet = myPlanet;
        this.type = buildingType;
        this.cost = 200;
        this.level = 1;
    }

    public Planet getPlanet(){
        return this.planet;
    }

    public String getType(){
        return this.type;
    }

    public int getCost(){
        return this.cost;
    }
}
