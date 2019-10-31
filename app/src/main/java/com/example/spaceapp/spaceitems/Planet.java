package com.example.spaceapp.spaceitems;

import com.example.spaceapp.spaceitems.types.ResourceTypes;

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

        Resource wood = new Resource(ResourceTypes.WOOD.getValue(), 1000);
        Resource stone = new Resource(ResourceTypes.STONE.getValue(), 1000);
        Resource water = new Resource(ResourceTypes.WATER.getValue(), 1000);

        Building woodBuilding = new Building(this, ResourceTypes.WOOD.getValue());
        Building waterBuilding = new Building(this, ResourceTypes.WATER.getValue());
        Building stoneBuilding = new Building(this, ResourceTypes.STONE.getValue());

        this.resourcesMap = new HashMap<String, Resource>();
        this.resourcesMap.put(ResourceTypes.WOOD.getValue(), wood);
        this.resourcesMap.put(ResourceTypes.STONE.getValue(), stone);
        this.resourcesMap.put(ResourceTypes.WATER.getValue(), water);

        this.buildings = new HashMap<String, Building>();
        this.buildings.put(ResourceTypes.WOOD.getValue(), woodBuilding);
        this.buildings.put(ResourceTypes.STONE.getValue(), stoneBuilding);
        this.buildings.put(ResourceTypes.WATER.getValue(), waterBuilding);
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
