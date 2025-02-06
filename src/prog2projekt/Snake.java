/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2projekt;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author adam.carlstrom
 */
public class Snake {
    private ClientManager host;
    private int dirX,dirY, lastUpdatedDirX,lastUpdatedDirY;
    ArrayList<Point> positions;
    private boolean isDead = false;
    public Snake(ClientManager host1, int dirX) {
        host = host1;
        positions = new ArrayList<>();
        setDirX(dirX);
        lastUpdatedDirX = dirX;
        lastUpdatedDirY = dirY;
    }
    
    public void setDirX(int x){
        dirX = x;
        dirY = 0;
    }
    
    public void setDirY(int y){
        dirY = y;
        dirX = 0;
    }

    public ClientManager getHost() {
        return host;
    }

    public int getDirX() {
        return dirX;
    }

    public int getDirY() {
        return dirY;
    }

    public void setPos(int i, int i0) {
        positions.add(new Point(i,i0));
    }
    
    public void removeLastPos(){
        positions.remove(0);
    }
    
    public Point getLastPoint(){
        return positions.get(positions.size()-1);
    }

    public ArrayList<Point> getPositions() {
        return positions;
    }

    public void isDead(boolean b) {
        isDead = b;
    }

    public boolean isDead() {
        return isDead;
    }

    public void changeDirection(String s) {
        int dir = (int)(Integer.parseInt(""+s.charAt(1)));
        switch(s.charAt(0)){
            case 'x':
                if(lastUpdatedDirX == 0){//det går att byta håll
                    dirY = 0;
                    if(dir == 2){
                        dirX = -1;
                    }else{
                        dirX = 1;
                    }
                }
                
                break;
            case 'y':
                if(lastUpdatedDirY == 0){//det går att byta håll
                    dirX = 0;
                    if(dir == 2){
                        dirY = -1;
                    }else{
                        dirY = 1;
                    }
                }
                break;
        }
    }

    public void newUpdate() {
        lastUpdatedDirX = dirX;
        lastUpdatedDirY = dirY;
    }
    
}
