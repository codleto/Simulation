package simulation;

import simulation.action.initaction.*;
import simulation.entity.staticobject.Food;
import simulation.map.WorldMap;

import java.util.Scanner;

public class Simulation {
    private static final int CONTINUE_GAME = 1;
    WorldMap worldMap = new WorldMap(5,5);
    WorldFactory worldFactory = new WorldFactory(worldMap);
    InitAction initAction = new InitAction();
    SimulationRunner runner = new SimulationRunner(worldMap);
    Thread thread = new Thread(runner);

    public void startSimulation() {

        GameConfig configWorld = initAction.readConfig(worldMap);
        worldFactory.initializeWorld(configWorld);
        thread.start();

        while (true) {
            int menuChoice = readNumber();
            if (menuChoice == 1) {
                runner.pause();
                while (true) {
                    System.out.println("Продолжить нажми - " + CONTINUE_GAME);
                    int pauseMenuChoice = readNumber();
                    if (pauseMenuChoice == CONTINUE_GAME) {
                        runner.resume();
                        break;
                    }
                }
            }

            if(menuChoice == 2){
                worldFactory.spawnEntity(new Food());
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

    private int readNumber(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            String input = scanner.nextLine().trim();
            if(input.matches("\\d+")){
                return Integer.parseInt(input);
            }
            System.out.println("Введи число без символов и букв");
        }
    }

}

