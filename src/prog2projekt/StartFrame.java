/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2projekt;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author karla
 */
public class StartFrame extends JFrame implements ActionListener{
    private JButton btnServer, btnClient;
    public StartFrame() {
        btnServer = new JButton("Create Server");
        btnClient = new JButton("Create Client");
        add(btnServer);
        add(btnClient);
        btnServer.addActionListener(this);
        btnClient.addActionListener(this);
        
        setLayout(new GridLayout(1,2));
        setPreferredSize(new Dimension(350,200));
        pack();
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o == btnServer){
            //när man skapat en server ska man inte längre kunna skapa en till server
            //när en server skapas måste den få en egen servermodel
            new ServerFrame(new ServerModel());
            btnServer.setEnabled(false);
        }else if(o == btnClient){
            //när en klient skapas får den sin egen klientmodel
            new ClientFrame(new ClientModel());
        }
    }
    
}
