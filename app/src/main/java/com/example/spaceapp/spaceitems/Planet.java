package com.example.spaceapp.spaceitems;

import java.util.HashMap;
import java.util.Map;

public class Planet {
    private String name;
    private int id;
    private int distance;
    private Map<String, Resource> resourcesMap;
    private Map<String, Building> buildings;
    private boolean captured;

    public Planet(String planetName, int id){
        this.name = planetName;
        this.id = id;

        this.distance = 4;
        this.captured = false;

        Resource wood = new Resource("Wood", 1000);
        Resource stone = new Resource("Stone", 1000);
        Resource water = new Resource("Water", 1000);

        Building woodBuilding = new Building(this, "Wood");
        Building waterBuilding = new Building(this, "Water");
        Building stoneBuilding = new Building(this, "Stone");

        this.resourcesMap = new HashMap<String, Resource>();
        this.resourcesMap.put("Wood", wood);
        this.resourcesMap.put("Stone", stone);
        this.resourcesMap.put("Water", water);

        this.buildings = new HashMap<String, Building>();
        this.buildings.put("Wood", woodBuilding);
        this.buildings.put("Stone", stoneBuilding);
        this.buildings.put("Water", waterBuilding);
    }

    public String getName(){
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public Map<String, Resource> getResources(){
        return this.resourcesMap;
    }

    public int getDistance(){
        return this.distance;
    }

    public Map<String, Building> getBuildings(){
        return this.buildings;
    }

    public void capture(){
        this.captured = true;
    }

    public boolean isCaptured(){
        return this.captured;
    }

    public void decreaseResource(String type, int amount){
        this.resourcesMap.get(type).decrease(amount);
    }

}
