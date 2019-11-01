package com.example.spaceapp.spaceitems;

import com.example.spaceapp.spaceitems.types.ResourceTypes;

public class Resource {
    private ResourceTypes type;
    private int amount;

    public Resource(ResourceTypes resourceType, int resourceAmount){
        this.type = resourceType;
        this.amount = resourceAmount;
    }

    public int getAmount(){
        return this.amount;
    }

    public ResourceTypes getType(){
        return this.type;
    }

    public void decrease(int num){
        this.amount -= num;
    }

    public void increase(int num){
        this.amount += num;
    }
}
