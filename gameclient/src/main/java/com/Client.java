package com;


import com.net.Network;

public class Client {

    Network network = new Network(Settings.HOST, Settings.PORT_ID);


    public Client(){
        System.out.println("Client started");
        network.init();
    }

    public static void main(String[] args){
        new Client();
    }
}
