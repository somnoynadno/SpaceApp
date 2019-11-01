package com.example.spaceapp.spaceitems;

import com.example.spaceapp.spaceitems.types.ResourceTypes;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Empire {
    private String name;
    private Map<Integer, Planet> planets;
    private Map<ResourceTypes, Resource> resourcesMap;
    private final int minRange = 200;
    private final int maxRange = 300;

    public Empire(String empireName){
        this.name = empireName;
        this.planets = new HashMap();

        // generate random amounts by default
        Resource wood = new Resource(ResourceTypes.WOOD,
                ThreadLocalRandom.current().nextInt(minRange, maxRange + 1));
        Resource stone = new Resource(ResourceTypes.STONE,
                ThreadLocalRandom.current().nextInt(minRange, maxRange + 1));
        Resource water = new Resource(ResourceTypes.WATER,
                ThreadLocalRandom.current().nextInt(minRange, maxRange + 1));

        this.resourcesMap = new HashMap();
        this.resourcesMap.put(ResourceTypes.WOOD, wood);
        this.resourcesMap.put(ResourceTypes.STONE, stone);
        this.resourcesMap.put(ResourceTypes.WATER, water);
    }

    public String getName(){
        return this.name;
    }

    public void addPlanet(Planet planet){
        this.planets.put(planet.getId(), planet);
    }

    public Map<Integer, Planet> getPlanets(){
        return this.planets;
    }

    public Map<ResourceTypes, Resource> getResources(){
        return this.resourcesMap;
    }
}
