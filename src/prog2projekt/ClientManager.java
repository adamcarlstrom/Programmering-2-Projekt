/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2projekt;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author adam.carlstrom
 */
public class ClientManager{
    private ServerSocket listeningSocket;
    private Socket clientSocket;
    private ServerModel model;
    private ManageListener manageListener;
    private DataOutputStream ut;
    public ClientManager(Socket clientSocket, ServerModel model) {
        this.model = model;
        this.clientSocket = clientSocket;
        try {
            ut = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        manageListener = new ManageListener(clientSocket, model,this);
        sendMessageToPlayer("Connection Successful");
    }
    
    public void sendMessageToPlayer(String msg){
        try {
            ut.writeUTF(msg);
        } catch (IOException ex) {
            System.out.println("Message unsuccessful");
            System.out.println(ex.getMessage());
        }
    }

   
}
