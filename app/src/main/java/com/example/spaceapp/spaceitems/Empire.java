package com.example.spaceapp.spaceitems;

import com.example.spaceapp.spaceitems.types.ResourceTypes;

import java.util.HashMap;
import java.util.Map;

public class Empire {
    private String name;
    private Map<Integer, Planet> planets;
    private Map<String, Resource> resourcesMap;

    public Empire(String empireName){
        this.name = empireName;
        this.planets = new HashMap();

        Resource wood = new Resource(ResourceTypes.WOOD.getValue(), 200);
        Resource stone = new Resource(ResourceTypes.STONE.getValue(), 200);
        Resource water = new Resource(ResourceTypes.WATER.getValue(), 200);

        this.resourcesMap = new HashMap();
        this.resourcesMap.put(ResourceTypes.WOOD.getValue(), wood);
        this.resourcesMap.put(ResourceTypes.STONE.getValue(), stone);
        this.resourcesMap.put(ResourceTypes.WATER.getValue(), water);
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

    public Map<String, Resource> getResources(){
        return this.resourcesMap;
    }
}
