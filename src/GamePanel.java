import javax.swing.JPanel;
import java.awt.Graphics;
/**
* Author: ThachPham
* Website: https://tpgamecoding.com
*/ 
public class GamePanel extends JPanel implements Runnable {
	
	private Thread thread;
	private Scene scene;
	
	public GamePanel() {
		scene = new Scene(this);
	}
	
	public void start() {
		thread = new Thread(this);
		thread.start();
	}
	
	private void update(float deltaTime) {
		Input.update(deltaTime);
		scene.update(deltaTime);
		repaint();
	}

	@Override
	public void run() {
		
		long period = 20;
        long beginTime = System.currentTimeMillis();
        long currentTime;
        float deltaTime = 0;
        while(true) {
            update(deltaTime);
            currentTime = System.currentTimeMillis();
            try {
                long sleepTime = period - currentTime + beginTime;
                if(sleepTime > 0) {
                    thread.sleep(sleepTime);
                }
                
            } catch (InterruptedException ex) {}
            currentTime = System.currentTimeMillis();
            deltaTime = (float) (currentTime - beginTime) / 1000;
            beginTime = currentTime;
        }
	}
	
	public void paint(Graphics g) {
		g.drawImage(scene.getRenderImage(), 0, 0, null);
	}

}
