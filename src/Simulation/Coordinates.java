package Simulation;

import Simulation.entity.Entity;

import java.util.Objects;

public class Coordinates {
    public Integer vertical;
    public final  Integer gorizantal;

    public static void genPositionTwo(Entity entity){
        entity.coordinates = new Coordinates(5,5);
    }

    public Coordinates(Integer vertical, Integer gorizantal) {
        this.vertical = vertical;
        this.gorizantal = gorizantal;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return vertical == that.vertical && Objects.equals(gorizantal, that.gorizantal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertical, gorizantal);
    }
}
