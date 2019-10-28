package com.example.spaceapp.spaceitems.utils;

public class IdGenerator {
    private int currentID;

    public IdGenerator(){
        this.currentID = 0;
    }

    public int giveNextID(){
        return this.currentID++;
    }
}
