package com.example.spaceapp.spaceitems;

import com.example.spaceapp.spaceitems.types.PlanetTypes;
import com.example.spaceapp.spaceitems.types.ResourceTypes;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Planet {
    private PlanetTypes name;
    private int id;
    private int distance;
    private Map<ResourceTypes, Resource> resourcesMap;
    private Map<ResourceTypes, Building> buildings;
    private boolean captured;
    private final int minDistance = 5;
    private final int maxDistance = 32;
    private final int minRange = 800;
    private final int maxRange = 1200;
    private Citizen citizens;

    public Planet(PlanetTypes planetName, int id){
        this.name = planetName;
        this.id = id;

        this.distance = ThreadLocalRandom.current().nextInt(minDistance, maxDistance + 1);
        this.captured = false;

        // generate random amount by default
        Resource wood = new Resource(ResourceTypes.WOOD,
                ThreadLocalRandom.current().nextInt(minRange, maxRange + 1));
        Resource stone = new Resource(ResourceTypes.STONE,
                ThreadLocalRandom.current().nextInt(minRange, maxRange + 1));
        Resource water = new Resource(ResourceTypes.WATER,
                ThreadLocalRandom.current().nextInt(minRange, maxRange + 1));

        Building woodBuilding = new Building(this, ResourceTypes.WOOD);
        Building waterBuilding = new Building(this, ResourceTypes.WATER);
        Building stoneBuilding = new Building(this, ResourceTypes.STONE);

        this.resourcesMap = new HashMap<ResourceTypes, Resource>();
        this.resourcesMap.put(ResourceTypes.WOOD, wood);
        this.resourcesMap.put(ResourceTypes.STONE, stone);
        this.resourcesMap.put(ResourceTypes.WATER, water);

        this.buildings = new HashMap<ResourceTypes, Building>();
        this.buildings.put(ResourceTypes.WOOD, woodBuilding);
        this.buildings.put(ResourceTypes.STONE, stoneBuilding);
        this.buildings.put(ResourceTypes.WATER, waterBuilding);
    }

    public PlanetTypes getName(){
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public Map<ResourceTypes, Resource> getResources(){
        return this.resourcesMap;
    }

    public int getDistance(){
        return this.distance;
    }

    public Map<ResourceTypes, Building> getBuildings(){
        return this.buildings;
    }

    public void capture(){
        this.captured = true;
        this.citizens = new Citizen(this);
    }

    public boolean isCaptured(){
        return this.captured;
    }

    public void decreaseResource(ResourceTypes type, int amount){
        this.resourcesMap.get(type).decrease(amount);
    }

    public Citizen getCitizens(){
        return this.citizens;
    }

}
