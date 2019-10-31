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
        this.cost = 200; // by default
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
            int resAmountInPlanet = planet.getResources().get(this.type).getAmount();
            if (resAmountInPlanet - amount > 0) {
                planet.decreaseResource(this.type, amount);
                this.tempStorage.increase(amount);
            }
            else{
                planet.decreaseResource(this.type, resAmountInPlanet);
                this.tempStorage.increase(resAmountInPlanet);
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
