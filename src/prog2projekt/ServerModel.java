/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2projekt;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Servermodellen som tar hand om serverns logiker
 * @author adam.carlstrom
 */
public class ServerModel {

    private ArrayList<ServerView> views;
    private int port = 5000;
    private int noOfConnections = 0;
    private ServerReciever reciever;
    private Map clients;
    private ArrayList<ClientManager> searchingUsers;
    private ArrayList<Game> games;

    public ServerModel() {
        views = new ArrayList<>();
        searchingUsers = new ArrayList<>();
        games = new ArrayList<>();
        reciever = new ServerReciever(this);
        clients = new HashMap<String, ClientManager>();
    }

    public void registerView(ServerView v) {
        views.add(v);
    }

    private void updateViews() {
        for (ServerView view : views) {
            view.update();
        }
    }

    public int getPort() {
        return port;
    }
    
    //när en uppkoppling sker ska mängden användare som är uppkopplade öka, vilket ska visas i serverinfoview
    public void increaseNoOfConnections() {
        noOfConnections++;
        updateViews();
    }
    public void decreaseNoOfConnections() {
        noOfConnections--;
        updateViews();
    }
    
    //När en klient kopplas till servern skickas deras Socket med som används i deras clientmanager hos servern.
    public void newClient(Socket clientSocket) {
        System.out.println("New Client Connected");
        clients.put(clientSocket.getInetAddress().getHostName(),
                new ClientManager(clientSocket, this));
        increaseNoOfConnections();
    }

    public int getNoOfConnections() {
        return noOfConnections;
    }
    
    public int getNoOfSearchingUsers(){
        return searchingUsers.size();
    }
    
    public int getNoOfGames(){
        return games.size();
    }
    
    /*När en användare försöker söka för spel skickas deras clientmanager med så
    * att det är lätt att skicka information till användare från ett spel senare.
    * Det finns en searchinglista som håller kolla på alla användare som söker.
    * När två användare söker placeras de i ett spel mot varandra.
    */
    public void search(ClientManager cm) {
        try{
            
            if(!searchingUsers.isEmpty()){
                //Är listan inte tom ska den tömmas och ett spel ska börjas med den sökande användaren och den i listan
                games.add(new Game(cm, searchingUsers.get(0),this));
                searchingUsers.remove(0);
                sendMessage(games.get(games.size()-1).getHost1(),"Game: Blue");
                sendMessage(games.get(games.size()-1).getHost2(),"Game: Yellow");
            }else{
                //om listan är tom ska användaren (deras clientmanager) läggas till i listan
                searchingUsers.add(cm);
                sendMessage(cm,"Searching...");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        updateViews();
    }
    
    //Skickar spelets board som en sträng till användarna som de sedan kan rita ut
    public void sendBoard(ClientManager host1, ClientManager host2, String[][] board) {
        String sBoard = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sBoard += board[i][j];
            }
        }
        
        sendMessage(host1,sBoard);
        sendMessage(host2,sBoard);
        
    }
    
    //Metoden skickar meddelande till klienten genom deras clientmanager
    private void sendMessage(ClientManager cm, String msg) {
        cm.sendMessageToPlayer(msg);
    }
    
    /*
    * 
    */
    public void changeDirection(ClientManager cm, String s) {
        for (int i = 0; i < games.size(); i++) {
            if(games.get(i).getHost1() == cm || games.get(i).getHost2() == cm){
                //existerar i detta game
                games.get(i).changeDirection(cm,s);
                break;
            }
        }
    }

    public void gameOver(ClientManager winner, ClientManager loser) {
        for (int i = 0; i < games.size(); i++) {
            if(games.get(i).getHost1() == winner || games.get(i).getHost2() == winner){
                games.remove(i);
            }
        }
        sendMessage(winner,"Winner!");
        sendMessage(loser,"Loser.");
        
        updateViews();
    }
    
    

}
