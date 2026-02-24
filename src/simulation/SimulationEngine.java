package simulation;

import simulation.action.turnaction.FoodService;
import simulation.action.turnaction.MoveAction;
import simulation.map.WorldMap;
import simulation.util.RendererMap;

class SimulationEngine implements Runnable {
    private static final int PAUSE = 1;
    private static final int ADD_FOOD = 2;
    private static final int EXIT_GAME = 3;
    private volatile boolean running = true;
    private volatile boolean paused = false;

    private final WorldMap map;

    public SimulationEngine(WorldMap map) {
        this.map = map;

    }

    private final RendererMap rendererMap = new RendererMap();

    @Override
    public void run() {
        final FoodService foodService = new FoodService(map);
        final MoveAction moveAction = new MoveAction(map);

        rendererMap.renderer(map);
        System.out.println(PAUSE + " = пауза | " + ADD_FOOD + " = Добавить еще еды | " + EXIT_GAME + " = выход");
        System.out.println("Количество шагов животных: " + moveAction.getMoveCount());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while (running) {
            if (!paused) {
                if (foodService.isFoodAvailableFor()) {
                    break;
                }
                moveAction.makeMoveCreatures(map);
                rendererMap.renderer(map);
                System.out.println(PAUSE + " = пауза | " + ADD_FOOD + " = Добавить еще еды | " + EXIT_GAME + " = выход");
                System.out.println("Количество шагов животных: " + moveAction.getMoveCount());

                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        exitSimulation();
    }

    public void exitSimulation() {
        running = false;
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }
}
