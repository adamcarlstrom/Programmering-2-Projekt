/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2projekt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author adam.carlstrom
 */
public class ClientReceiver implements Runnable{
    private Socket so;
    private ClientModel model;
    private DataInputStream in;
    private DataOutputStream ut;
    private Thread t;
    public ClientReceiver(Socket so, ClientModel model) {
        System.out.println("Creating ClientReceiver");
        this.so = so;
        this.model = model;
        try {
            in = new DataInputStream(so.getInputStream());
            ut = new DataOutputStream(so.getOutputStream());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        while(true) {
            try {
                String s = in.readUTF();
                System.out.println("Receiving");
                System.out.println(s);
                model.newMessage(s);
            }
            catch(Exception e){
                break; //Behöver inte fortsätta vänta på meddelanden?
            }
        }
    }
    
}
