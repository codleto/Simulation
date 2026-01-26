package simulation.entity;

import simulation.map.Coordinates;

public abstract class Entity {
    protected String skin;
    public Coordinates coordinates;

    public String getSkin() {
        return skin;
    }
}

