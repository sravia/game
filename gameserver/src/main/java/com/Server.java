package com;

import com.engine.GameEngine;
import com.engine.World;
import com.net.Network;

public class Server {

    private Network network;
    private GameEngine engine;

    public Server(){
        System.out.println("Server started!");
        try{
            network = new Network(Settings.PORT_ID);
            engine = new GameEngine();
            engine.start();

            World.getWorld().setPort(43594);
            World.getWorld().setWorldId(1);
            World.getWorld().init(engine);
            if (World.getWorld().getBackgroundLoader().getPendingTaskAmount() > 0) {
                System.out.println("Waiting for pending background loading tasks...");
                World.getWorld().getBackgroundLoader().waitForPendingTasks();
            }
            World.getWorld().getBackgroundLoader().shutdown();

            network.init();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new Server();
    }

}
