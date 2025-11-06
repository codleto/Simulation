package Simulation;

import Simulation.entity.Animal.Herbivore;
import Simulation.entity.Animal.Predator;
import Simulation.entity.StaticObject.Grass;
import Simulation.entity.StaticObject.Rock;
import Simulation.entity.StaticObject.Tree;

import static Simulation.Map.*;
import static Simulation.Renderer.renderer;

public class Main {
    public static void main(String[] args) {
        Herbivore h = new Herbivore(5);
        setEntity(h);

        Predator predator = new Predator(2);
        setEntity(predator);

        setEntity(new Grass(1));
        setEntity(new Grass(1));
        setEntity(new Grass(1));
        setEntity(new Rock(3));
        setEntity(new Rock(1));
        setEntity(new Rock(9));
        setEntity(new Tree(1));
        setEntity(new Tree(2));
        setEntity(new Tree(4));
        setEntity(new Tree(3));

        while(true) {
            h.makeMove();
            renderer();
            predator.makeMove();
            renderer();
            if(mapEat() || Search.breakk.size() > 1){
                Search.breakk.clear();
                break;
            }
        }
    }

}