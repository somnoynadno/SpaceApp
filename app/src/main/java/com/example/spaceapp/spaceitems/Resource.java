package com.example.spaceapp.spaceitems;

public class Resource {
    private String type;
    private int amount;

    public Resource(String resourceType, int resourceAmount){
        this.type = resourceType;
        this.amount = resourceAmount;
    }

    public int getAmount(){
        return this.amount;
    }

    public String getType(){
        return this.type;
    }

    public void decrease(int num){
        this.amount -= num;
    }

    public void increase(int num){
        this.amount += num;
    }
}
