package Simulation.entity;

import Simulation.Coordinates;

public abstract class Entity {
    private String skin;
    public int id;
    public Coordinates coordinates;

    public String getSkin() {
        return skin;
    }
    public void setSkin(String skin){
        this.skin = skin;
    }

}

