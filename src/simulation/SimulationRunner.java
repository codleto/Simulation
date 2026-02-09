package simulation;

import simulation.action.turnaction.Grass;
import simulation.action.turnaction.Move;
import simulation.map.WorldMap;
import simulation.util.RendererMap;

class SimulationRunner implements Runnable {
    private static final int PAUSE = 1;
    private static final int ADD_FOOD = 2;
    private static final int EXIT_GAME = 3;
    private volatile boolean running = true;
    private volatile boolean paused = false;

    private final WorldMap map;

    public SimulationRunner(WorldMap map) {
        this.map = map;

    }

    RendererMap rendererMap = new RendererMap();

    @Override
    public void run() {
        final Grass grass = new Grass(map);
        final Move move = new Move(map);

        rendererMap.renderer(map);
        System.out.println(PAUSE + " = пауза | " + ADD_FOOD + " = Добавить еще еды | " + EXIT_GAME + " = выход");
        System.out.println("Количество шагов животных: " + move.getMoveCount());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while (running) {
            if (!paused) {
                if (grass.isFoodAvailableFor()) {
                    break;
                }
                move.makeMoveCreatures(map);
                rendererMap.renderer(map);
                System.out.println(PAUSE + " = пауза | " + ADD_FOOD + " = Добавить еще еды | " + EXIT_GAME + " = выход");
                System.out.println("Количество шагов животных: " + move.getMoveCount());

                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
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
