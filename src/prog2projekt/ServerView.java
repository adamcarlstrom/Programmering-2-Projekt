/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2projekt;

import javax.swing.JPanel;

/**
 * Den här klassen är en View klass från MVC-modellen
 * @author adam.carlstrom
 */
public abstract class ServerView extends JPanel{
    private ServerModel serverModel;
    
    
    
    public ServerView(ServerModel m){
         m.registerView(this); 
         this.serverModel=m;
    }
    
    public ServerModel getServerModel() {
            return serverModel;
    }
    public abstract void update();

}
