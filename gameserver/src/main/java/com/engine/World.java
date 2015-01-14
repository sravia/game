package com.engine;

import com.engine.event.Event;
import com.engine.event.EventManager;
import com.engine.event.events.EntityUpdateEvent;
import com.engine.event.events.MemoryCleanerEvent;
import com.engine.event.events.RestoreEvent;
import com.engine.task.Task;
import com.engine.task.tasks.SessionLoginTask;
import com.engine.tick.Tick;
import com.engine.tick.TickManager;
import com.game.entity.player.Player;
import com.net.packet.PacketManager;
import com.net.packet.decoders.PacketDecoder;
import com.net.packet.decoders.PacketOpcode;
import com.utils.BlockingExecutorService;
import com.utils.EntityList;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class World {

    private static final World world = new World();

    private BlockingExecutorService backgroundLoader = new BlockingExecutorService(Executors.newSingleThreadExecutor());

    private GameEngine engine;

    private int worldId, port;

    private TickManager tickManager;

    private EventManager eventManager;

    private EntityList<Player> onlinePlayers = new EntityList<Player>(2000);

    public World() {
        backgroundLoader.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                //load npcs
                System.out.println("Loading NPCs?");
                return null;
            }
        });
    }

    public BlockingExecutorService getBackgroundLoader() {
        return backgroundLoader;
    }

    public void init(GameEngine engine) throws Exception {
        this.engine = engine;
        this.eventManager = new EventManager(engine);
        this.tickManager = new TickManager();
        this.registerGlobalEvents();
        this.loadConfiguration();
    }

    private void registerGlobalEvents() {
        submit(new EntityUpdateEvent());
        submit(new MemoryCleanerEvent());
        submit(new RestoreEvent());
    }

    public void submit(Event event) {
        this.eventManager.submit(event);
    }

    public void submit(Task task) {
        this.engine.pushTask(task);
    }

    public void submit(final Tick tickable) {
        submit(new Task() {
            @Override
            public void execute(GameEngine context) {
                World.getWorld().getTickManager().submit(tickable);
            }
        });
    }

    public TickManager getTickManager() {
        return tickManager;
    }

    public GameEngine getEngine() {
        return engine;
    }

    public void load(final Player player, final boolean lobby) {
        engine.submitWork(new Runnable() {
            @Override
            public void run() {
                player.getChannel().pipeline().context("handler").write(player);
                engine.pushTask(new SessionLoginTask(player));
            }
        });
    }

    public void register(final Player player) {
        /*
        Loggin player to lobby/game?
        * */
    }

    public EntityList<Player> getPlayers() {
        return onlinePlayers;
    }

    public void unregister(final Player player) {
        /*
        * save player data
        * logout from game
        * close session
        * */
    }

    public void setWorldId(int worldId) {
        this.worldId = worldId;
    }

    public int getWorldId() {
        return worldId;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public static World getWorld() {
        return world;
    }

    private void loadConfiguration() throws Exception {
        File[] files = new File("./src/main/java/com/net/packet/decoders/").listFiles();
        for (File file : files) {
            Class<?> c = Class.forName("com.net.packet.decoders." + file.getName().replaceAll(
                    ".java", ""));

            if ((c.getSuperclass() == PacketDecoder.class)) {
                PacketDecoder packet = (PacketDecoder) c.newInstance();

                if (packet.getClass().getAnnotation(PacketOpcode.class) == null) {
                    throw new Exception("PacketOpcode missing:" + packet);
                }

                int packetOpcodes[] = packet.getClass().getAnnotation(
                        PacketOpcode.class).value();

                for (int opcode : packetOpcodes) {
                    PacketManager.getPacketManager().bind(opcode, packet);
                    System.out.println("Bound " + packet.toString() + " to opcode : " + opcode);
                }
            }
        }
    }

}
