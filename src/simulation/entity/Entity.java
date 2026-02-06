package simulation.entity;

import simulation.map.Coordinates;

public abstract class Entity {
    protected String skin;
    private Coordinates coordinates;

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public  Coordinates getCoordinates() {
        return this.coordinates;
    }

    public String getSkin() {
        return skin;
    }
}

