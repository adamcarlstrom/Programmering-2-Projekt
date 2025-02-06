/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2projekt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adam.carlstrom
 */
public class ServerReciever implements Runnable{
    private ServerSocket listeningSocket;
    private Socket clientSocket;
    private DataInputStream in;
    private DataOutputStream ut;
    private int port;
    public Thread t;
    private ServerModel model;
    private static int noOfConnections = 0;
    public ServerReciever(ServerModel model){
        
        this.model = model;
        port = 5000;
        
        try {
            listeningSocket = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        t = new Thread(this);
        t.start();
    }
    
    public static void increaseNoOfConnections() {
        noOfConnections++;
    }
    
    public static void decreaseNoOfConnections() {
        noOfConnections--;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Socket clientSocket = listeningSocket.accept();
                System.out.println(clientSocket.getInetAddress().getHostName() + " connected.");
                model.newClient(clientSocket);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
