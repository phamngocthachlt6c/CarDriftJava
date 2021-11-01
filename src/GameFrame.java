import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
* Author: ThachPham
* Website: https://tpgamecoding.com
*/ 
public class GameFrame extends JFrame implements KeyListener{
	
	private GamePanel gamePanel;
	
	public GameFrame() {
		setSize(900, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamePanel = new GamePanel();
		add(gamePanel);
		addKeyListener(this);
		setVisible(true);
		gamePanel.start();
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
	        case KeyEvent.VK_UP:
	            Input.setKeyVertical(Input.UP);
	            break;
	        case KeyEvent.VK_LEFT:
	        	Input.setKeyHorizontal(Input.LEFT);
	            break;
	        case KeyEvent.VK_RIGHT:
	        	Input.setKeyHorizontal(Input.RIGHT);
	            break;
	        case KeyEvent.VK_DOWN:
	        	Input.setKeyVertical(Input.DOWN);
	        	break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN)
			Input.setKeyVertical(Input.NONE);
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT)
			Input.setKeyHorizontal(Input.NONE);
	}
}
