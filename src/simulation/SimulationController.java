package simulation;

import simulation.action.initaction.*;
import simulation.entity.staticobject.Grass;
import simulation.map.WorldMap;

import java.util.Scanner;

public class SimulationController {
    private static final int CONTINUE_GAME = 1;
    private final WorldMap worldMap = new WorldMap(5,5);
    private final WorldGenerator worldGenerator = new WorldGenerator(worldMap);
    private final InitAction initAction = new InitAction();
    private final SimulationEngine runner = new SimulationEngine(worldMap);
    private final Thread thread = new Thread(runner);

    public void startSimulation() {

        final GameConfig configWorld = initAction.readConfig(worldMap);
        worldGenerator.initializeWorld(configWorld);
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
                worldGenerator.spawnEntity(new Grass());
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
        final Scanner scanner = new Scanner(System.in);
        while (true){
            String input = scanner.nextLine().trim();
            if(input.matches("\\d+")){
                return Integer.parseInt(input);
            }
            System.out.println("Введи число без символов и букв");
        }
    }

}

