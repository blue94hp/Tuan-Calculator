/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Calculator;

import java.awt.BorderLayout;
import java.awt.Component;
import java.net.URL;
import javax.media.*;  
import javax.swing.JPanel;

public class SimpleAudioPlayer extends JPanel {
    
    public SimpleAudioPlayer(URL mediauUrl) {
        //initComponents();
        setLayout(new BorderLayout());
        try {
            Player mediaPlayer=Manager.createRealizedPlayer(new MediaLocator(mediauUrl));
            Component video=mediaPlayer.getVisualComponent();
            Component control=mediaPlayer.getControlPanelComponent();
            if (video!=null) {
                add(video, BorderLayout.CENTER);          
            }
            add(control, BorderLayout.SOUTH);            
            //mediaPlayer.start();
        } catch (Exception ex) {
        }
    }
}
