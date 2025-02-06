/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2projekt;

import java.awt.Point;

/**
 *
 * @author adam.carlstrom
 */
public abstract class Square {
    
    private Point p;
    
    public Square(Point p){
        this.p = p;
    }
    
    public Point getPoint(){
        return p;
    }
    public  int getX(){
        return (int)(p.getX());
    }
    public  int getY(){
        return (int)(p.getY());
    }
    
    public void setNewPoint(Point p){
        this.p = p;
    }
    
}
