import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Scene {
	
	private BufferedImage renderedImage;
	private int width, height;
	
	private JPanel context;
	
	private Car car;
	
	private BufferedImage road1Map;
	
	public Scene(JPanel context) {
		this.context = context;
		car = new Car();
		try {
			road1Map = ImageIO.read(new File("road1.png"));
		} catch (IOException e) {
		}
	}

	public void update(float deltaTime) {
		car.update();
	}
	
	public BufferedImage getRenderImage() {
		
		if(renderedImage == null) {
            this.width = context.getWidth();
            this.height = context.getHeight();
            // Prevent user see render process:
            // using buffered image to avoid seeing under items
            // when drawing, it draws every layer, so user can see all layers when it
            // draw, if use a buffered image, user just see a picture of frame after it render
            renderedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        }
		
		Graphics g = renderedImage.getGraphics();
//		g.setColor(Color.WHITE);
//		g.fillRect(0, 0, context.getWidth(), context.getHeight());
		g.drawImage(road1Map, 0, 0, context.getWidth(), context.getHeight(), context);
		car.draw(g);
		
		return renderedImage;
	}
}
