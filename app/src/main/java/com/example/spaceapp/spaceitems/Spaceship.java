package com.example.spaceapp.spaceitems;

public class Spaceship {
    private Planet targetPlanet;
    private boolean ready;
    private int timeLeft;

    public Spaceship(){
        this.targetPlanet = null;
        this.ready = true;
        this.timeLeft = 0;
    }

    public void capture(Planet planet){
        this.targetPlanet = planet;
        this.ready = false;
        this.timeLeft = planet.getDistance();
        System.out.println("capture " + planet.getName());
    }

    public boolean isReady(){
        return this.ready;
    }

    public void tick(){
        this.timeLeft--;
        if (this.timeLeft == 0){
            this.ready = true;
            this.targetPlanet.capture();
        }
    }

    public int getTimeLeft(){
        return this.timeLeft;
    }

    public Planet getTargetPlanet(){
        return this.targetPlanet;
    }
}
