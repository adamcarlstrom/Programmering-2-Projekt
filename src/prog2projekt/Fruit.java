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
public class Fruit extends Square{
    Fruit(int i, int i0) {
        super(new Point(i,i0));
    }
    public Point getRandomPoint(){
        return new Point((int)(Math.random()*14), (int)(Math.random()*14));
    }
    
}
