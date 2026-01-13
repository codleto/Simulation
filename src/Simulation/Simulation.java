package Simulation;

import Simulation.action.TurnAction;
import Simulation.Entity.StaticObject.Eat;

import static Simulation.Map.isFoodAvailableFor;
import static Simulation.Map.spawnEntity;
import static Simulation.Renderer.renderer;
import static Simulation.action.TurnAction.nextTurn;
import static Simulation.action.InitAction.*;

public class Simulation {
    public static int moveCount = 0;
    public static void startSimulation() {
        SimulationRunner runner = new SimulationRunner();

        renderer();
        chooseMapSize();
        configureSheepCount();
        setWolfAttackPower();
        setWolfSpeed();
        initializeWorld();
        runner.start();

        OUT :
        while (true) {
            int menuChoice = readNumber();
            if (menuChoice == 1) {
                runner.pause();
                while (true) {
                    System.out.println("Продолжить нажми - 1 Для выхода нажми - 2");
                    int pauseMenuChoice = readNumber();
                        if(pauseMenuChoice == 1){
                            runner.resume();
                            break;
                        }

                    if (pauseMenuChoice == 2) {
                        runner.exitSimulation();
                        break OUT;
                    }
                }
            }

            if(menuChoice == 2){
                spawnEntity(new Eat());
                continue;
            }

            if (menuChoice == 3) {
                runner.exitSimulation();
                break;
            }
            else {
                System.out.println("""
                        Введи 1,2 или 3.
                        1 = пауза | 2 = Добавить еще еды | 3 = выход""");
            }
        }

    }

}

class SimulationRunner extends Thread {
    public volatile boolean running = true;
    public volatile boolean paused = false;

    public void run() {
        renderer();
        System.out.println("1 = пауза |" + " 2 = Добавить еще еды |" + " 3 = выход");
        System.out.println("Количество шагов животных: " + Simulation.moveCount);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while (running) {
            if(!paused) {
                if (isFoodAvailableFor()) {
                    break;
                }
                nextTurn();
                renderer();
                System.out.println("1 = пауза |" + " 2 = Добавить еще еды |" + " 3 = выход");
                System.out.println("Количество шагов животных: " + Simulation.moveCount);

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
        running = false;
    }

    public void pause(){
        paused = true;
    }

    public void resume(){
        paused = false;
    }
}

