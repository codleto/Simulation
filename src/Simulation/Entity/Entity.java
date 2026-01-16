package Simulation.Entity;

import Simulation.Coordinates;

public abstract class Entity {
    private String skin;
    public Coordinates coordinates;

    public String getSkin() {
        return this.skin;
    }
    public void setSkin(String skin){
        this.skin = skin;
    }

}

