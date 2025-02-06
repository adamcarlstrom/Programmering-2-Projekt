/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2projekt;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adam.carlstrom
 */
public class ClientModel implements SaveToFile{
    private ArrayList<ClientView> views;
    private ClientSender sender;
    private Boolean connected = false, isGamePlayed = false, isSearching = false;
    private String receivedMsg, playingColor;
    private String messageToViews;
    private String[][] board;
    private ArrayList<String> history;
    public ClientModel() {
        receivedMsg = "";
        playingColor = "";
        views = new ArrayList<>();
        history = new ArrayList<>();
        board = new String[15][15];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = "";
            }
        }
    }
    public void registerView(ClientView v) {
        views.add(v);
    }
    private void updateViews() {
        for(ClientView view : views) {
            view.update();
        }
    }

    public void connectToServer(String address, String port) {
        sender = new ClientSender(address,port,this);
    }

    public void connectedToServer() {
        connected = true;
        updateViews();
    }

    public Boolean isConnected() {
        return connected;
    }

    public String getReceivedMsg() {
        return receivedMsg;
    }

    public void newMessage(String s) {
        receivedMsg = s;
        if(receivedMsg.equals("Connection Successful")){
            connectedToServer();
        }else if(s.length() > 200){
            System.out.println("Receiving Board");
            for (int i = 0; i < (int)(s.length()); i++) {
                board[(int)(i/15)][(int)(i%15)] = String.valueOf(s.charAt(i));
            }
        }else if(receivedMsg.equals("Winner!") || receivedMsg.equals("Loser.")){
            isGamePlayed = false;
            history.add(receivedMsg);
        }else if(receivedMsg.equals("Game: Blue") || receivedMsg.equals("Game: Yellow")){
            setPlayingColor(receivedMsg.substring(5,receivedMsg.length()));
            isGamePlayed = true;
            isSearching = false;
            if(receivedMsg.equals("Game: Blue")){
                history.add(receivedMsg + "  ");
            }else{
                history.add(receivedMsg);
            }
        }
        updateViews();
    }

    public void connectionFailed() {
        connected = false;
        messageToViews = "ConnectionFailed";
        updateViews();
    }

    public String getMessageToViews() {
        return messageToViews;
    }

    public void searchGame() {
        isSearching = true;
        setPlayingColor("");
        sender.searchGame();
    }
    
    public void setBoard(String[][] b){
        this.board = b;
    }
    
    public String[][] getBoard(){
        return board;
    }

    public void changeDirection(char c, int i) {
        String s = "" + c + i;
        sender.sendMessage(s);
    }

    public boolean isGamePlayed() {
        return isGamePlayed;
    }

    public void saveToFile() {
        String s = "";
        for (int i = 0; i < history.size(); i++) {
            if(i%2 == 0){
                s += history.get(i) + "\t";
            }else{
                s+= history.get(i) + "\n";
            }
        }
        
        PrintWriter utFil = null;
        try {
            utFil = new PrintWriter(new BufferedWriter(new FileWriter("Game_History.txt")));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        utFil.println(s);
        utFil.close();
    }

    public boolean isSearching() {
        return isSearching;
    }

    public String getPlayingColor() {
        return playingColor;
    }

    public void setPlayingColor(String playingColor) {
        this.playingColor = playingColor;
    }

    
    
}
