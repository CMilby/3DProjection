package me.cmilby;

import java.awt.Dimension;

import javax.swing.JFrame;
 
public class Projection {
    
    public static void main(String[] args) {
    	JFrame frame = new JFrame("3D Projection");
    	ProjectionPanel panel = new ProjectionPanel();
    	
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setResizable(false);
    
    	frame.add(panel);
    	panel.addComponentsToPane(frame.getContentPane());
    
    	frame.getContentPane().setPreferredSize(new Dimension(600, 620));
    	frame.pack();
    	frame.setLocationRelativeTo(null);
    	
    	frame.setVisible(true);
    }
}
