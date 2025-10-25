package Simulation;

import Simulation.Animal.Creature;

import java.util.Objects;

public class Coordinates {
    public  Vertical vertical;
    public final  Integer gorizantal;

    public void gorizantalRight(Creature creature) {}

    public Coordinates(Vertical vertical, Integer gorizantal) {
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
