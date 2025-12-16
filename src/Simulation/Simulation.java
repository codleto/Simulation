package Simulation;

import Simulation.action.TurnAction;
import Simulation.entity.StaticObject.Grass;

import static Simulation.Map.isFoodAvailableFor;
import static Simulation.Map.spawnEntity;
import static Simulation.Renderer.renderer;
import static Simulation.action.TurnAction.nextTurn;
import static Simulation.action.InitAction.*;

public class Simulation {
    public static int moveCount = 0;
    public static void mainn() {

        Console concolee = new Console();
        renderer();
        chooseMapSize();
        configureSheepCount();
        setWolfAttackPower();
        setWolfSpeed();
        initializeWorld();
        concolee.start();

        OUT :
        while (true) {
            int x = readNumber();
            if (x == 1) {
                concolee.pauseSimulation();
                while (true) {
                    System.out.println("Продолжить нажми - 1 Для выхода нажми - 2");
                    int i = readNumber();
                        if(i == 1){
                            concolee.continueSimulation();
                            break;
                        }

                    if (i == 2) {
                        concolee.exitSimulation();
                        break OUT;
                    }
                }
            }

            if(x == 2){
                spawnEntity(new Grass());
                continue;
            }

            if (x == 3) {
                concolee.exitSimulation();
                break;
            }
            else {
                System.out.println("Введи 1,2 или 3. 1 = пауза |" + " 2 = Добавить еще еды |" + " 3 = выход");
            }
        }

    }

}

class Console extends Thread {
    public volatile boolean runneble = true;
    public volatile boolean pause = false;

    public void run() {
        renderer();
        System.out.println("1 = пауза |" + " 2 = Добавить еще еды |" + " 3 = выход");
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while (runneble) {
            if(!pause) {
                if (isFoodAvailableFor()) {
                    break;
                }
                nextTurn();
                renderer();
                System.out.println("1 = пауза |" + " 2 = Добавить еще еды |" + " 3 = выход");

                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (!TurnAction.movesThisTurn.isEmpty()) {
                    TurnAction.movesThisTurn.clear();
                    continue;
                }
                break;

            }
        }
    }

    public void exitSimulation(){
        runneble = false;
    }

    public void pauseSimulation(){
        pause = true;
    }

    public void continueSimulation(){
        pause = false;
    }
}

