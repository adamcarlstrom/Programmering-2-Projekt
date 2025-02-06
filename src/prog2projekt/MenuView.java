/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2projekt;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author adam.carlstrom
 */
public class MenuView extends ClientView implements ActionListener{
    private ClientModel model;
    private JLabel lbl1;
    private JPanel connectionPanel, connectedPanel;
    private JLabel lblAddress,lblPort,lblConnection, lblTitle, lblMessage, lblPlayingColor;
    private JTextField tfAddress,tfPort;
    private JButton btnConnect, btnSearch, btnSave;
    private GameView gw;
    public MenuView(ClientModel model, GameView gw) {
        super(model);
        this.model = model;
        this.gw = gw;
        
        setBorder(new TitledBorder("Menu"));
    
        connectionPanel = new JPanel();
        lblAddress = new JLabel("Address: ");
        lblPort = new JLabel("Port: ");
        lblConnection = new JLabel("...");
        tfAddress = new JTextField("localhost");
        tfPort = new JTextField("5000");
        btnConnect = new JButton("Connect");
        
        connectionPanel.setLayout(new GridLayout(4,2));
        connectionPanel.add(lblAddress);
        connectionPanel.add(tfAddress);
        connectionPanel.add(lblPort);
        connectionPanel.add(tfPort);
        connectionPanel.add(btnConnect);
        connectionPanel.add(lblConnection);
        
        add(connectionPanel);
        
        connectedPanel = new JPanel();
        lblTitle = new JLabel("Snake Multiplayer");
        btnSearch = new JButton("Search Game");
        btnSave  = new JButton("Save to File");
        lblMessage = new JLabel("...");
        lblPlayingColor = new JLabel("");
        
        connectedPanel.add(lblTitle);
        connectedPanel.add(btnSearch);
        connectedPanel.add(btnSave);
        connectedPanel.add(lblMessage);
        connectedPanel.add(lblPlayingColor);
        add(connectedPanel);
        connectedPanel.setLayout(new BoxLayout(connectedPanel, BoxLayout.Y_AXIS));
        connectedPanel.hide();
        
        btnConnect.addActionListener(this);
        btnSearch.addActionListener(this);
        btnSave.addActionListener(this);
    }

    @Override
    public void update() {
        if(model.isConnected()){
            lblConnection.setText(model.getReceivedMsg());
            connectionPanel.hide();
            connectedPanel.show();
        }else if(model.getMessageToViews().equals("ConnectionFailed")){
            lblConnection.setText("No connection");
        }
        if(!model.isGamePlayed() && !model.isSearching()){
            btnSearch.setEnabled(true);
        }else{
            btnSearch.setEnabled(false);
        }
        if(model.getReceivedMsg().length() < 200){
            lblMessage.setText(model.getReceivedMsg());
        }
        lblPlayingColor.setText(model.getPlayingColor());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o == btnConnect){
            try{
                String address = tfAddress.getText();
                String port = tfPort.getText();
                model.connectToServer(address,port);
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }else if(o == btnSearch){
            model.searchGame();
            btnSearch.setEnabled(false);
            gw.requestFocus();
        }else if(o == btnSave){
            model.saveToFile();
        }
        revalidate();
    }
    
}
