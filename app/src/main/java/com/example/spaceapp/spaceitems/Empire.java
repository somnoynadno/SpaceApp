package com.example.spaceapp.spaceitems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Empire {
    private String name;
    private List<Planet> planets;
    private Map<String, Resource> resourcesMap;

    public Empire(String empireName){
        this.name = empireName;
        this.planets = new ArrayList<>();

        Resource wood = new Resource("Wood", 200);
        Resource stone = new Resource("Stone", 200);
        Resource water = new Resource("Water", 200);

        this.resourcesMap = new HashMap<String, Resource>();
        this.resourcesMap.put("Wood", wood);
        this.resourcesMap.put("Stone", stone);
        this.resourcesMap.put("Water", water);
    }

    public String getName(){
        return this.name;
    }

    public List<Planet> getPlanets(){
        return this.planets;
    }

    public Map<String, Resource> getResources(){
        return this.resourcesMap;
    }
}
