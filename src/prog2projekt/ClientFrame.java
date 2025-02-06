/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2projekt;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author adam.carlstrom
 */
public class ClientFrame extends JFrame{
    private GameView gameView;
    private MenuView menuView;
    public ClientFrame(ClientModel model){
        
        gameView = new GameView(model);
        menuView = new MenuView(model, gameView);
        
        setLayout(new BoxLayout(getContentPane(),BoxLayout.X_AXIS));
        
        gameView.setPreferredSize(new Dimension (700,700));
        menuView.setPreferredSize(new Dimension (200,700));
        
        add(gameView);
        add(menuView);
        
        
        setPreferredSize(new Dimension(900,700));
        pack();
        setVisible(true);
        //setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
