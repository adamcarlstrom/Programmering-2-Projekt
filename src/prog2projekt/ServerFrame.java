/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2projekt;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *This class does this..
 * @author adam.carlstrom
 * @version 1.0
 */
public class ServerFrame extends JFrame{
    private ServerInfoView serverInfoView;
    
    /**
     * Det här sätter igång JFrame till servern som visas
     * @param model är en servermodel vilket är serverns model
     */
    public ServerFrame(ServerModel model){
        serverInfoView = new ServerInfoView(model);
        
        add(serverInfoView);
        
        setPreferredSize(new Dimension(350,200));
        pack();
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
