package me.cmilby;

import java.awt.Graphics;

import java.awt.geom.Point2D;

public class Cube {
	
	public Point3D[] vertices;
	
	public Cube(double size, Point3D center) {
		vertices = new Point3D[8];
      	
      	vertices[0] = new Point3D((-1.0 * size) + center.x, (1.0 * size) + center.y, (-1.0 * size) + center.z);
      	vertices[1] = new Point3D((-1.0 * size) + center.x, (1.0 * size) + center.y, (1.0 * size) + center.z);
      	vertices[2] = new Point3D((1.0 * size) + center.x, (1.0 * size) + center.y, (1.0 * size) + center.z);
      	vertices[3] = new Point3D((1.0 * size) + center.x, (1.0 * size) + center.y, (-1.0 * size) + center.z);
      	vertices[4] = new Point3D((-1.0 * size) + center.x, (-1.0 * size) + center.y, (-1.0 * size) + center.z);
      	vertices[5] = new Point3D((-1.0 * size) + center.x, (-1.0 * size) + center.y, (1.0 * size) + center.z);
      	vertices[6] = new Point3D((1.0 * size) + center.x, (-1.0 * size) + center.y, (1.0 * size) + center.z);
      	vertices[7] = new Point3D((1.0 * size) + center.x, (-1.0 * size) + center.y, (-1.0 * size) + center.z);
	}
	
	public void drawCube(Graphics g) {
		Point3D[] topPoints = {vertices[0], vertices[1], vertices[2], vertices[3]};
		Point3D[] botPoints = {vertices[4], vertices[5], vertices[6], vertices[7]};
    	for (int i = 0; i < topPoints.length; i++) {
    		if (i == 0) {
    			Point2D top1 = projectPoint(topPoints[topPoints.length - 1]);
    			Point2D top2 = projectPoint(topPoints[i]);
    			Point2D bot1 = projectPoint(botPoints[botPoints.length - 1]);
    			Point2D bot2 = projectPoint(botPoints[i]);
    			g.drawLine(((int) top1.getX()), ((int) top1.getY()), ((int) top2.getX()), ((int) top2.getY()));
    			g.drawLine(((int) bot1.getX()), ((int) bot1.getY()), ((int) bot2.getX()), ((int) bot2.getY()));
    			g.drawLine(((int) top1.getX()), ((int) top1.getY()), ((int) bot1.getX()), ((int) bot1.getY()));
    			g.fillOval(((int) top2.getX()) - 3, ((int) top2.getY()) - 3, 6, 6);
    			g.fillOval(((int) bot2.getX()) - 3, ((int) bot2.getY()) - 3, 6, 6);
    		} else {
    			Point2D top1 = projectPoint(topPoints[i - 1]);
    			Point2D top2 = projectPoint(topPoints[i]);
    			Point2D bot1 = projectPoint(botPoints[i - 1]);
    			Point2D bot2 = projectPoint(botPoints[i]);
    			g.drawLine(((int) top1.getX()), ((int) top1.getY()), ((int) top2.getX()), ((int) top2.getY()));
    			g.drawLine(((int) bot1.getX()), ((int) bot1.getY()), ((int) bot2.getX()), ((int) bot2.getY()));
    			g.drawLine(((int) top1.getX()), ((int) top1.getY()), ((int) bot1.getX()), ((int) bot1.getY()));
    			g.fillOval(((int) top2.getX()) - 3, ((int) top2.getY()) - 3, 6, 6);
    			g.fillOval(((int) bot2.getX()) - 3, ((int) bot2.getY()) - 3, 6, 6);
    		}
    	}
   	}
   	
   	public Point2D projectPoint(Point3D point) {
   		return (new Point2D.Double(point.x + (point.z / 2.0), point.y - (point.z / 2.0)));
   	}
}
