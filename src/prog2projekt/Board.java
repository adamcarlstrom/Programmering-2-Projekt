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
public class Board {
    private Snake snake1, snake2;
    private String[][] board;
    private Fruit[] fruits;
    public Board(ClientManager host1, ClientManager host2) {
        snake1 = new Snake(host1,1);
        snake2 = new Snake(host2,-1);
        board = new String[15][15];
        resetBoard();
        setUpPositions(snake1,1,13,"1");
        setUpPositions(snake2,13,1,"2");
        
        fruits = new Fruit[5];
        fruits[0] = new Fruit(7,7);
        fruits[1] = new Fruit(7,5);
        fruits[2] = new Fruit(7,9);
        fruits[3] = new Fruit(5,7);
        fruits[4] = new Fruit(9,7);
        
        for (int i = 0; i < fruits.length; i++) {
            board[fruits[i].getX()][fruits[i].getY()] = "f";
        }
    }

    private void setUpPositions(Snake snake1, int i, int i0,String s) {
        if(s.equals("1")){
            for (int j = 0; j < 3; j++) {
                snake1.setPos(i+j,i0);
                if(j == 2){
                    board[i+j][i0] = "" + (Integer.valueOf(s) + 2);   
                }else{
                    board[i+j][i0] = s;
                }
            }
        }else if(s.equals("2")){
            for (int j = 0; j <3; j++) {
                snake1.setPos(i-j,i0);
                if(j == 2){
                    board[i-j][i0] = "" + (Integer.valueOf(s) + 2);   
                }else{
                    board[i-j][i0] = s;
                }
            }
        }
        
        
    }
    
    public String[][] getBoard(){
        return board;
    }

    public Boolean updatePositions() {
        snake1.newUpdate();
        snake2.newUpdate();
        if(snake1.getDirX() != 0){
            snake1.setPos((int)(snake1.getLastPoint().getX()) + snake1.getDirX(),(int)(snake1.getLastPoint().getY()));
        }else if(snake1.getDirY() != 0){
            snake1.setPos((int)(snake1.getLastPoint().getX()),(int)(snake1.getLastPoint().getY())  + snake1.getDirY());
        }
        
        //kolla kollision med mat innan man tar bort
        if(!foodCollision(snake1)){
            snake1.removeLastPos();
        }
        
        if(checkCollision(snake1,snake2)){
            return false;
        }
        
        if(snake1.getLastPoint().getX() > 14 || snake1.getLastPoint().getX() < 0 || snake1.getLastPoint().getY() > 14 || snake1.getLastPoint().getY() < 0){
            snake1.isDead(true);
            return false;
        }
        
        if(snake2.getDirX() != 0){
            snake2.setPos((int)(snake2.getLastPoint().getX()) + snake2.getDirX(),(int)(snake2.getLastPoint().getY()));
        }else if(snake2.getDirY() != 0){
            snake2.setPos((int)(snake2.getLastPoint().getX()),(int)(snake2.getLastPoint().getY())  + snake2.getDirY());
        }
        if(!foodCollision(snake2)){
            snake2.removeLastPos();
        }
        
        if(checkCollision(snake2,snake1)){
            return false;
        }
        
        if(snake2.getLastPoint().getX() > 14 || snake2.getLastPoint().getX() < 0 || snake2.getLastPoint().getY() > 14 || snake2.getLastPoint().getY() < 0){
            snake2.isDead(true);
            return false;
        }
        
        resetBoard();
        System.out.println(snake1.getPositions());
        for (int i = 0; i < snake1.getPositions().size(); i++) {
            if(i == snake1.getPositions().size()-1){
                board[(int)(snake1.getPositions().get(i).getX())][(int)(snake1.getPositions().get(i).getY())] = "3";
            }else{
                board[(int)(snake1.getPositions().get(i).getX())][(int)(snake1.getPositions().get(i).getY())] = "1";
            }
        }
        
        System.out.println(snake2.getPositions());
        for (int i = 0; i < snake2.getPositions().size(); i++) {
            if(i == snake2.getPositions().size()-1){
                board[(int)(snake2.getPositions().get(i).getX())][(int)(snake2.getPositions().get(i).getY())] = "4";
            }else{
                board[(int)(snake2.getPositions().get(i).getX())][(int)(snake2.getPositions().get(i).getY())] = "2";
            }
        }
        for (int i = 0; i < fruits.length; i++) {
            board[fruits[i].getX()][fruits[i].getY()] = "f";
        }
        
        return true;
        
    }

    private void resetBoard() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                board[i][j] = " ";
            }
        }
    }

    public Snake getSnake1() {
        return snake1;
    }
    
    public Snake getSnake2() {
        return snake2;
    }

    public void changeDirection(ClientManager cm, String s) {
        if(cm == snake1.getHost()){//host1 vill byta håll
            snake1.changeDirection(s);
        }else if(cm == snake2.getHost()){//host2 vill byta håll
            snake2.changeDirection(s);
        }
    }

    private boolean checkCollision(Snake s, Snake s2) {
        //kolla kollision på sig själv
        for (int i = 0; i < s.getPositions().size(); i++) {
            for (int j = i+1; j < s.getPositions().size(); j++) {
                if(s.getPositions().get(i).equals(s.getPositions().get(j))){
                    //kollision på sig själv
                    s.isDead(true);
                    return true;
                }
            }
        }
        
        //kolla kollision på motståndare
        for (int i = 0; i < s.getPositions().size(); i++) {
            for (int j = 0; j < s2.getPositions().size(); j++) {
                if(s.getPositions().get(i).equals(s2.getPositions().get(j))){
                    //kollision på motståndare
                    s.isDead(true);
                    return true;
                }
            }
        }
        
        return false;
    }

    private boolean foodCollision(Snake snake1) {
        for (int i = 0; i < snake1.getPositions().size(); i++) {
            for (int j = 0; j < fruits.length; j++) {
                if(snake1.getPositions().get(i).equals(fruits[j].getPoint())){
                    //food is eaten
                    board[fruits[j].getX()][fruits[j].getY()] = "";
                    newFoodPosition(fruits[j]);
                    
                    return true;
                }
            }
        }
        return false;
    }

    private void newFoodPosition(Fruit fruit) {
        Point p = null;
        do{
             p = fruit.getRandomPoint();
        }while(board[(int)(p.getX())][(int)(p.getY())].equals(""));
        fruit.setNewPoint(p);
    }

}
