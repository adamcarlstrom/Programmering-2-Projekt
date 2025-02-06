/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2projekt;

import javax.swing.JPanel;

/**
 *
 * @author adam.carlstrom
 */
public abstract class ClientView extends JPanel{
    private ClientModel clientModel;
    
    public ClientView(ClientModel m){
        m.registerView(this); 
        this.clientModel=m;
    }
    
    public ClientModel getClientModel() {
        return clientModel;
    }
    
    public abstract void update();
}
