package com.example.spaceapp.spaceitems;

public class Spaceship {
    private Planet targetPlanet;
    private boolean isReady;
    private int timeLeft;

    public Spaceship(){
        this.targetPlanet = null;
        this.isReady = true;
        this.timeLeft = 0;
    }

    public void capture(Planet planet){
        this.targetPlanet = planet;
        this.isReady = false;
        this.timeLeft = planet.getDistance();
    }
}
