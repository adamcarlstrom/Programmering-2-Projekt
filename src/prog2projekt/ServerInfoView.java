/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2projekt;

import java.awt.GridLayout;
import java.net.InetAddress;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

/**
 *
 * @author adam.carlstrom
 */
public class ServerInfoView extends ServerView{
    private ServerModel model;
    private JLabel lblNoOfConnections, lblNoOfSearchingUsers, lblNoOfGamesPlayed;
    private int port;
    
    
    /*
    * Den här metoden visar serverns kopplingar och liknande genom en JPanel
    * @param model är en koppling till servermodellen
    */
    public ServerInfoView(ServerModel model) {
        super(model);
        this.model = model;
        
        lblNoOfConnections = new JLabel("0");
        lblNoOfSearchingUsers = new JLabel("0");
        lblNoOfGamesPlayed= new JLabel("0");
        port = this.model.getPort();
        
        try{
            setLayout(new GridLayout(5,2));
            add(new JLabel("IP Address: ")); add(new JLabel(InetAddress.getLocalHost().getHostAddress()));
            add(new JLabel("Port: ")); add(new JLabel("" + port));
            add(new JLabel("Number of connections: "));
            add(lblNoOfConnections);
            add(new JLabel("Users searching: "));
            add(lblNoOfSearchingUsers);
            add(new JLabel("Games Played: "));
            add(lblNoOfGamesPlayed);
            setBorder(new TitledBorder("ServerView"));
        }catch(Exception e){
            System.out.println("Problem occured");
        }
    }

    
    /*
    * Metoden hämtar värden från modellen och uppdaterar info som visas
    */
    @Override
    public void update() {
        lblNoOfConnections.setText(String.valueOf(model.getNoOfConnections()));
        lblNoOfSearchingUsers.setText(String.valueOf(model.getNoOfSearchingUsers()));
        lblNoOfGamesPlayed.setText(String.valueOf(model.getNoOfGames()));
    }
    
}
