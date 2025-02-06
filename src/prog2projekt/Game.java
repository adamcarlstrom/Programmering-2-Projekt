/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2projekt;

/**
 *
 * @author adam.carlstrom
 */
public class Game implements Runnable{
    private ClientManager host1,host2;
    private Thread t;
    private Board b;
    private ServerModel model;
    public Game(ClientManager host1, ClientManager host2, ServerModel m) {
        this.host1 = host1;
        this.host2 = host2;
        this.model = m;
        
        b = new Board(host1,host2);
        
        model.sendBoard(host1,host2,b.getBoard());
        
        t = new Thread(this);
        t.start();
    }

    public ClientManager getHost1() {
        return host1;
    }

    public ClientManager getHost2() {
        return host2;
    }

    public Thread getT() {
        return t;
    }

    public Board getB() {
        return b;
    }
    
    

    @Override
    public void run() {
        while(!Thread.interrupted()){
            try{
                Thread.sleep(1000);
                if(!b.updatePositions()){
                    if(b.getSnake1().isDead()){//snake1 dog
                        //host2 win
                        System.out.println("Snake1: Blue dog");
                        model.gameOver(host2,host1);
                    }else if(b.getSnake2().isDead()){//snake2 dog
                        //host1 win
                        System.out.println("Snake2: Yellow dog");
                        model.gameOver(host1,host2);
                    }
                    Thread.interrupted();
                    break;
                }else{
                    model.sendBoard(host1,host2,b.getBoard());
                }
            }catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Game Stopped");
    }

    public void changeDirection(ClientManager cm, String s) {
        b.changeDirection(cm,s);
    }
    
    
    
}
