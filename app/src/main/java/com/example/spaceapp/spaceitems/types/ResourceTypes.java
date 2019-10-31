package com.example.spaceapp.spaceitems.types;

public enum ResourceTypes {
    WOOD("Wood"),
    WATER("Water"),
    STONE("Stone");

    private final String name;

    private ResourceTypes(String n){
        name = n;
    }

    public String getValue(){
        return name;
    }
}
