package me.cmilby;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProjectionPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JLabel[] labels = {new JLabel(" X-Rotation:"), new JLabel(" Y-Rotation:"), new JLabel(" Z-Rotation:")};
	private JTextField[] txtFields = {new JTextField("0.0", 5), new JTextField("0.0", 5), new JTextField("0.0", 5)};
	private JButton button = new JButton("Compute Rotations");

	private Cube cube = new Cube(100.0, new Point3D(300, 300, 0));
	
	public ProjectionPanel() {
      	setBackground(Color.WHITE);
      	setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.BLACK);
		g.drawLine(300, 25, 300, 575);
		g.drawLine(25, 300, 575, 300);
		g.drawLine(50, 550, 550, 50);
		
		g.setFont(new Font("SANS_SERIF", 1, 20));
		g.drawString("X", 580, 295);
		g.drawString("Y", 305, 25);
		g.drawString("Z", 555, 45);
		
		g.setColor(Color.RED);
		cube.drawCube(g);
	}
	
	public void addComponentsToPane(Container pane) {
		Box bx = Box.createHorizontalBox();
		for (int i = 0; i < labels.length; i++) {
			labels[i].setFont(new Font("SANS_SERIF", 1, 17));
			txtFields[i].setFont(new Font("SANS_SERIF", 0, 15));
			bx.add(labels[i]);
			bx.add(txtFields[i]);
		}
		
		bx.add(button);
		button.addActionListener(this);
		pane.add(bx, BorderLayout.PAGE_END);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (Util.isNumeric(txtFields[0].getText()) 
				&& Util.isNumeric(txtFields[1].getText()) 
				&& Util.isNumeric(txtFields[2].getText())) {
			double[] center = {300, 300, 0};
			double[] rotations = {Double.parseDouble(txtFields[0].getText()), Double.parseDouble(txtFields[1].getText()), 
					Double.parseDouble(txtFields[2].getText())};
			for (int i = 0; i < cube.vertices.length; i++) {
				double[] points = {cube.vertices[i].x, cube.vertices[i].y, cube.vertices[i].z};
				cube.vertices[i] = Util.rotateAll(center, points, rotations);
				/*cube.vertices[i] = Util.rotateX(points[0], points[1], points[2], 
						Double.parseDouble(txtFields[0].getText()), 300, 300, 0);
				cube.vertices[i] = Util.rotateY(points[0], points[1], points[2], 
						Double.parseDouble(txtFields[1].getText()), 300, 300, 0);
				cube.vertices[i] = Util.rotateZ(points[0], points[1], points[2], 
						Double.parseDouble(txtFields[2].getText()), 300, 300, 0);*/
			}
			repaint();
		} 
	}
}
