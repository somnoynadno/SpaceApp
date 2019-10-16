package com.example.spaceapp.spaceitems;

public class Building {
    private String type;
    private Planet planet;
    private int cost;
    private int level;
    private boolean producingStatus;
    private Resource tempStorage;

    public Building(Planet myPlanet, String buildingType){
        this.planet = myPlanet;
        this.type = buildingType;
        this.cost = 200;
        this.level = 0;
        this.producingStatus = false;
        this.tempStorage = new Resource(type, 0);
    }

    public Planet getPlanet(){
        return this.planet;
    }

    public String getType(){
        return this.type;
    }

    public int getCost(){
        return this.cost;
    }

    public void produce(){
        System.out.println("Start producing");
        this.producingStatus = true;
    }

    public void tick(){
        if (this.producingStatus){
            int amount = this.level*10;
            planet.decreaseResource(this.type, amount);
            this.tempStorage.increase(amount);
            if (planet.getResources().get(type).getAmount() <= 0){
                this.producingStatus = false;
            }
        }
    }

    public Resource getTempStorage(){
        return this.tempStorage;
    }

    public void levelUp(){
        this.level += 1;
        this.cost += 50;
    }

    public int getLevel(){
        return this.level;
    }
}
