package com.example.spaceapp.spaceitems.types;

public enum PlanetTypes {
    MARS("Mars"),
    EARTH("Earth"),
    NEPTUNE("Neptune");

    private final String name;

    private PlanetTypes(String n){
        name = n;
    }

    public String getValue(){
        return name;
    }
}
