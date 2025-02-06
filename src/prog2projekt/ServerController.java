/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2projekt;

import javax.swing.JPanel;

/**
 * Den här klassen är en Controller från MVC-modellen
 * @author adam.carlstrom
 */
public abstract class ServerController extends JPanel {
    private ServerModel serverModel;
    
    public ServerController(ServerModel m){
         this.serverModel = m;
    }
    
    public ServerModel getServerModel() {
        return serverModel;
    }
    
}
