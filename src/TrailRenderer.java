import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Color;

public class TrailRenderer {
	List<Point> points = new ArrayList<Point>();
	List<Color> alphaColors = new ArrayList<Color>();
	
	private Car car;
	private float oldCarX, oldCarY;
	private float r, angleOffset;
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
			g.setColor(alphaColors.get(i++));
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
		if(points.size() > 100) {
			Point point = points.get(0);
			points.remove(0);
			points.add(point);
			point.x = x;
			point.y = y;
		} else {

			Point point = new Point(x, y);
			points.add(point);
		}
	}
	
	static class Point {
		float x, y;
		Point(float x, float y) {
			this.x = x;
			this.y = y;
		}
	}
}
