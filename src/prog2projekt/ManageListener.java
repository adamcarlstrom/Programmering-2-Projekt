/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2projekt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adam.carlstrom
 */
public class ManageListener implements Runnable{
    private Thread t;
    private Socket clientSocket;
    private DataInputStream in;
    private DataOutputStream ut;
    private ServerModel model;
    private ClientManager cm;
    public ManageListener(Socket clientSocket, ServerModel model, ClientManager cm) {
        System.out.println("New managelistener");
        this.clientSocket = clientSocket;
        this.model = model;
        this.cm = cm;
        t = new Thread(this);
        
        try {
            in = new DataInputStream(clientSocket.getInputStream());
            ut = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        t.start();
    }
    
    
    @Override
    public void run() {
        while(true) {
            try {
                System.out.println("Manager väntar på meddelanden");
                String s = in.readUTF();
                System.out.println(s);
                if(s.equals("search")){
                    model.search(cm);
                }else if(s.length() == 2){
                    //Byta riktning
                    model.changeDirection(cm,s);
                }
            }
            catch(Exception e){
                //Användare lämnade serverkoppling
                model.decreaseNoOfConnections();
                break; //Behöver inte fortsätta vänta på meddelanden?
            }
        }
        System.out.println(clientSocket.getInetAddress().getHostName() + " disconnected.");
        try {
            clientSocket.close();    
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
