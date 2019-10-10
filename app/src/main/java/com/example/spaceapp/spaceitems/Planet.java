package com.example.spaceapp.spaceitems;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Planet {
    private String name;
    private int distance;
    private Map<String, Resource> resourcesMap;
    private List<Building> buildings;

    public Planet(String planetName){
        this.name = planetName;
        this.distance = 30;
        this.buildings = null;

        Resource wood = new Resource("Wood", 1000);
        Resource stone = new Resource("Stone", 1000);
        Resource water = new Resource("Water", 1000);

        this.resourcesMap = new HashMap<String, Resource>();
        this.resourcesMap.put("Wood", wood);
        this.resourcesMap.put("Stone", stone);
        this.resourcesMap.put("Water", water);
    }

    public String getName(){
        return this.name;
    }

    public Map<String, Resource> getResources(){
        return this.resourcesMap;
    }

    public int getDistance(){
        return this.distance;
    }

    public List<Building> getBuildings(){
        return this.buildings;
    }

}
