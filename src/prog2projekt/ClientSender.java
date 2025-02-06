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
public class ClientSender {
    private Socket so;
    private ClientReceiver receiver;
    private ClientModel model;
    private DataInputStream in;
    private DataOutputStream ut;
    public ClientSender(String address, String port, ClientModel model) {
        this.model = model;
        try {
            so = new Socket(address, Integer.valueOf(port));
            in = new DataInputStream(so.getInputStream());
            ut = new DataOutputStream(so.getOutputStream());
            receiver = new ClientReceiver(so,model);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            model.connectionFailed();
        }
    }

    public void searchGame() {
        try {
            ut.writeUTF("search");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void sendMessage(String msg){
        try {
            ut.writeUTF(msg);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
