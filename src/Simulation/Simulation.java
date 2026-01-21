package Simulation;

import Simulation.action.TurnAction;
import Simulation.Entity.StaticObject.Food;

import static Simulation.World.isFoodAvailableFor;
import static Simulation.World.spawnEntity;
import static Simulation.Renderer.renderer;
import static Simulation.action.TurnAction.nextTurn;
import static Simulation.action.InitAction.*;

public class Simulation {
    private static final int CONTINUE_GAME = 1;
    private static final int EXIT_GAME_FROM_PAUSE = 2;

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
                    System.out.println("Продолжить нажми - " + CONTINUE_GAME +" Для выхода нажми - " + EXIT_GAME_FROM_PAUSE);
                    int pauseMenuChoice = readNumber();
                        if(pauseMenuChoice == CONTINUE_GAME){
                            runner.resume();
                            break;
                        }

                    if (pauseMenuChoice == EXIT_GAME_FROM_PAUSE) {
                        runner.exitSimulation();
                        break OUT;
                    }
                }
            }

            if(menuChoice == 2){
                spawnEntity(new Food());
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
    private static final int PAUSE = 1;
    private static final int ADD_FOOD = 2;
    private static final int EXIT_GAME = 3;

    private volatile boolean running = true;
    private volatile boolean paused = false;

    public void run() {
        renderer();
        System.out.println(PAUSE + " = пауза | " + ADD_FOOD + " = Добавить еще еды | " + EXIT_GAME + " = выход");
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
                System.out.println(PAUSE + " = пауза | " + ADD_FOOD + " = Добавить еще еды | " + EXIT_GAME + " = выход");
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

