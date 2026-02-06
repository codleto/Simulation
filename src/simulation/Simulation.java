package simulation;

import simulation.action.Grass;
import simulation.action.initaction.*;
import simulation.action.turnaction.NextTurn;
import simulation.action.turnaction.Spawn;
import simulation.entity.staticobject.Food;
import simulation.map.WorldMap;
import simulation.util.RendererMap;

public class Simulation {
    private static final int CONTINUE_GAME = 1;
    WorldMap worldMap = new WorldMap();
    Spawn spawn = new Spawn(worldMap);
    SimulationRunner runner = new SimulationRunner(worldMap);
    WorldFactory worldFactory = new WorldFactory(spawn);
    InitAction initAction = new InitAction();
    Thread thread = new Thread(new SimulationRunner(worldMap));
    Input input = new Input();

    public int moveCount = 0;//todo: счетчик должен быть в отдельном классе или в другом а может и нет

    public void startSimulation() {

        GameConfig configWorld = initAction.readConfig();
        worldMap.setMapSize(configWorld.mapSize());
        worldFactory.initializeWorld(configWorld);
        thread.start();

        while (true) {
            int menuChoice = input.readNumber();
            if (menuChoice == 1) {
                runner.pause();
                while (true) {
                    System.out.println("Продолжить нажми - " + CONTINUE_GAME);
                    int pauseMenuChoice = input.readNumber();
                    if (pauseMenuChoice == CONTINUE_GAME) {
                        runner.resume();
                        break;
                    }
                }
            }

            if(menuChoice == 2){
                spawn.spawnEntity(new Food());
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

class SimulationRunner implements Runnable {
    private static final int PAUSE = 1;
    private static final int ADD_FOOD = 2;
    private static final int EXIT_GAME = 3;
    private volatile boolean running = true;
    private volatile boolean paused = false;

    private final WorldMap map;

    public SimulationRunner(WorldMap map){
        this.map = map;

    }
    private final NextTurn nextTurn = new NextTurn();

    RendererMap rendererMap = new RendererMap();

    public void run() {
        final Grass grass = new Grass(map);

        rendererMap.renderer(map);
        System.out.println(PAUSE + " = пауза | " + ADD_FOOD + " = Добавить еще еды | " + EXIT_GAME + " = выход");
        System.out.println("Количество шагов животных: " );
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while (running) {
            if(!paused) {
                if (grass.isFoodAvailableFor()) {
                    break;
                }
                nextTurn.nextTurn(map);
                rendererMap.renderer(map);
                System.out.println(PAUSE + " = пауза | " + ADD_FOOD + " = Добавить еще еды | " + EXIT_GAME + " = выход");
                System.out.println("Количество шагов животных: ");

                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
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

