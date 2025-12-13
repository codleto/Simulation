package Simulation;

import Simulation.action.TurnAction;
import static Simulation.Map.mapEat;
import static Simulation.Renderer.renderer;
import static Simulation.action.TurnAction.nextTurn;
import java.util.Scanner;
import static Simulation.action.InitAction.*;

public class Simulation {
    public static int moveCount = 0;
    public static void mainn() {
        Scanner scanner = new Scanner(System.in);
        Console concolee = new Console();
        renderer();
        mapSize();
        setSheepCount();
        wolfAttackPower();
        wolfSpeed();
        initializeWorld();
        concolee.start();

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
                        break;
                    }
                }
            }

            if (x == 2) {
                concolee.exitSimulation();
                break;
            }
            else {
                System.out.println("Введи 1 или 2. 1 = пауза" + " 2 = выход");
            }
        }

    }

}

class Console extends Thread {
    public volatile boolean runneble = true;
    public volatile boolean pause = false;

    public void run() {
        renderer();
        System.out.println("1 = пауза" + " 2 = выход");
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while (runneble) {
            if(!pause) {
                if (mapEat()) {
                    break;
                }
                nextTurn();
                renderer();
                System.out.println("1 = пауза" + " 2 = выход");

                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (!TurnAction.breakk.isEmpty()) {
                    TurnAction.breakk.clear();
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

