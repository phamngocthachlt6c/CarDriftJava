import java.util.ArrayList;
import java.util.List;

import javax.swing.colorchooser.ColorSelectionModel;

import java.awt.Graphics;
import java.awt.Color;

public class TrailRenderer {
	List<Point> points = new ArrayList<Point>();
	List<Color> alphaColors = new ArrayList<Color>();
	
	private Car car;
	private float oldCarX, oldCarY;
	private float r, angleOffset;
	
	public boolean emitting = true;
	
	public TrailRenderer(Car car, float r, float angleOffset) {
		this.car = car;
		oldCarX = car.x;
		oldCarY = car.y;
		this.angleOffset = angleOffset;
		this.r = r;
		
		for(int i = 0; i < 101;i ++) {
			alphaColors.add(new Color(0, 0, 0, i));
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		if(points.size() == 0) return;
		Point prevPoint = points.get(0);
		int i = 0;
		for(Point point : points) {
			if(!point.draw) g.setColor(alphaColors.get(0)); else g.setColor(Color.BLACK);
//			g.setColor(alphaColors.get(i++));
			g.drawLine((int) prevPoint.x, (int) prevPoint.y, (int) point.x, (int)point.y);
			prevPoint = point;
		}
	}
	
	public void update() {
		double radian = (car.angle + 90) * 3.14 / 180;
		double radianOffset = (angleOffset + 90) * 3.14 / 180;
		
		double realOffsetX = Math.cos(radian + radianOffset) * r;
		double realOffsetY = Math.sin(radian + radianOffset) * r;
		if(car.x != oldCarX && car.y != oldCarY) {
			addPoint(car.x + (float) realOffsetX, car.y + (float) realOffsetY);
		}
	}
	
	public void addPoint(float x, float y) {
		Point point;
		if(points.size() > 100) {
			point = points.get(0);
			points.remove(0);
			point.x = x;
			point.y = y;
		} else {
			point = new Point(x, y);
			
		}
		point.draw = emitting;
		points.add(point);
	}
	
	static class Point {
		float x, y;
		boolean draw = true;
		Point(float x, float y) {
			this.x = x;
			this.y = y;
		}
	}
}
