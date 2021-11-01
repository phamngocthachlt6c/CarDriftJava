import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.RenderingHints;
public class Car {
	
	private float x = 200, y = 200;
	private int width = 40, height = 60;
	
	private float angle = -90;
	
	private float acelerationInput = 0;
	
	private Vector2 vector2;
	
	private BufferedImage carSprite;
	
	RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	
	public Car() {
		try {
			carSprite = ImageIO.read(new File("car.png"));
		} catch (IOException e) {
		}
	}
	
	public void draw(Graphics g) {
		float angleToDraw = angle + 90;
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHints(rh);
		g2d.rotate(Math.toRadians(angleToDraw), x, y);
		g.setColor(Color.RED);
		g.drawLine((int) x, (int) y, (int) x, (int) y - 50);
//		g.fillRect((int) (x - width / 2), (int) (y - height / 2), width, height);
		g.drawImage(carSprite, (int) (x - width /2), (int) (y - height / 2), width, height, null);
		
		g2d.rotate(Math.toRadians(-angleToDraw), x + width / 2, y + height / 2);
		
		if(vector2 != null) {
			g.setColor(Color.BLACK);
			g.drawLine((int) x, (int) y, (int) (x + vector2.x), (int) (y + vector2.y));
		}
	}
	
	public void update() {
		Vector2 inputVector = Vector2.zero();
		inputVector.x = Input.getAxis("horizontal");
		inputVector.y = Input.getAxis("vertical");
		
		if(inputVector.x > 1) inputVector.x = 1;
		if(inputVector.y > 2) inputVector.y = 2;
		
		angle += inputVector.x * 4;
		if(angle >= 360) angle -= 360;
		else if(angle <= -360) angle += 360;
		
		if(inputVector.y != 0) 
			acelerationInput = inputVector.y;
		else if(acelerationInput > 0)
			acelerationInput -= 0.03;
		else if(acelerationInput < 0)
			acelerationInput += 0.03;
		Vector2 engineForceVector = Vector2.zero();
		
		engineForceVector.y = acelerationInput;
		vector2 = engineForceVector = transformVectorByAngle(engineForceVector);
		addForce(engineForceVector);
		
		
	}
	
	public void addForce(Vector2 vector2) {
		x += vector2.x;
		y += vector2.y;
	}
	
	private Vector2 transformVectorByAngle(Vector2 input) {
		Vector2 vector = Vector2.zero();
		float radian = (angle) * 3.14f / 180;
		System.out.println(angle + ", " + radian);
		vector.x = input.x * (float) Math.sin(radian) - input.y * (float) Math.cos(radian);
		vector.y = input.x * (float) Math.cos(radian) - input.y * (float) Math.sin(radian);
		return vector;
	}
}
