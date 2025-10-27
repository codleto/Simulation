package Simulation;

public abstract class Entity {
    public Coordinates coordinates; // todo: нельзя хранить координаты в сущности
    public String skin;
    public String getSkin() {
        return skin;
    }
}

