package com.example.spaceapp.spaceitems;

import com.example.spaceapp.spaceitems.types.ResourceTypes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Citizen {
    private int amount;
    private double mood; // from 0 to 100
    private double reproductionRate;
    private Planet planet;
    private Resource wantedResource;
    private int amountMinRange = 10;
    private int amountMaxRange = 100;
    private final int boostRate = 20;

    private static final List<ResourceTypes> VALUES =
            Collections.unmodifiableList(Arrays.asList(ResourceTypes.values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    private static ResourceTypes getRandomResource(){
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public Citizen(Planet planet){
        this.planet = planet;
        this.mood = 50; // by default
        this.amount = 2;
        this.reproductionRate = 0.05;

        this.wantedResource = new Resource(getRandomResource(),
                ThreadLocalRandom.current().nextInt(amountMinRange, amountMaxRange + 1));
    }

    public double getMood(){
        return this.mood;
    }

    public int getAmount(){
        return this.amount;
    }

    public Resource getWantedResource(){
        return this.wantedResource;
    }

    public void tick(){
        this.mood -= 0.6;
        this.reproductionRate = (this.mood/1000)*4;
        this.reproduceCitizen();
//        System.out.println(planet.getName() + " " + this.amount + " " + this.mood);
    }

    public void boostMood(){
        this.mood += boostRate;
        if (this.mood > 100){
            this.mood = 100;
        }
        this.wantedResource = new Resource(getRandomResource(),
                ThreadLocalRandom.current().nextInt(amountMinRange, amountMaxRange + 1));
    }

    private void reproduceCitizen(){
        double randNumber = Math.random();
        if (randNumber < this.reproductionRate){
            this.amount += 1;
        }
    }
}
