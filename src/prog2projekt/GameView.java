/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2projekt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author adam.carlstrom
 */
public class GameView extends ClientView{
    private ClientModel model;
    private JLabel lbl1;
    public GameView(ClientModel m){
        super(m);
        model = m;
        
        repaint();
        
        setFocusable(true);
        
        KeyListener k = new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                char c = e.getKeyChar();
                c = Character.toLowerCase(c);
                System.out.println("Keytyped " +c);
                switch(c){
                    case 'w'://uppåt (y-)
                        m.changeDirection('y',2);
                        break;
                    case 'a'://vänster (x-)
                        m.changeDirection('x',2);
                        break;
                    case 's'://nbedåt (y+)
                        m.changeDirection('y',1);
                        break;
                    case 'd'://höger (x+)
                        m.changeDirection('x',1);
                        break;
                }
            }
        };
        addKeyListener(k);
    }
    
    @Override
    public void update() {
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setFocusable(true);
        
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                switch((i+j)%2){
                    case 0: //even
                        g.setColor(new Color(158,232,197));
                        break;
                    case 1: //odd
                        g.setColor(new Color(255,239,148));
                        break;
                }
                g.fillRect(getWidth()/15*i,getHeight()/15*j,getWidth()/15,getHeight()/15);
            }
        }
            
        if(model.getBoard() != null){
            for (int i = 0; i < model.getBoard().length; i++) {
                for (int j = 0; j < model.getBoard()[0].length; j++) {
                    switch (model.getBoard()[i][j]) {
                        case "1":
                            //snake1
                            g.setColor(Color.BLUE);
                            g.fillRect((int)(getWidth()/15 * i),(int)(getHeight()/15 * j),getWidth()/15,getHeight()/15);
                            break;
                        case "2":
                            //snake2
                            g.setColor(Color.YELLOW);
                            g.fillRect((int)(getWidth()/15 * i),(int)(getHeight()/15 * j),getWidth()/15,getHeight()/15);
                            break;
                        case "f":
                            //fruit
                            g.setColor(Color.RED);
                            g.fillOval((int)(getWidth()/15 * i),(int)(getHeight()/15 * j),getWidth()/15,getHeight()/15);
                            break;
                        case "3":
                            g.setColor(new Color(103, 30, 230));
                            g.fillRect((int)(getWidth()/15 * i),(int)(getHeight()/15 * j),getWidth()/15,getHeight()/15);
                            break;
                        case "4":
                            g.setColor(new Color(255, 238, 0));
                            g.fillRect((int)(getWidth()/15 * i),(int)(getHeight()/15 * j),getWidth()/15,getHeight()/15);
                            break;
                        default:
                            //Empty square
                            break;
                    }
                    
                }
            }
            
            if(!model.isGamePlayed()){
                g.setColor(Color.BLACK);
                if(model.getReceivedMsg().equals("Loser.") || model.getReceivedMsg().equals("Winner!")){
                    g.drawString(model.getReceivedMsg(), getWidth()/2, getHeight()/2);
                }else{
                    g.drawString("NO GAME!", getWidth()/2, getHeight()/2);
                }
            }
            
        }
    }
    
}
