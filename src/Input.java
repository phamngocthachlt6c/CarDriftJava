
public class Input {
	public static final int NONE = 0;
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;
	
	public static final float INCREASE_FACTOR = 5f;
	
	private static int horizontalKey = NONE;
	private static int verticalKey = NONE;

	public static float horizontal;
	public static float vertical;
	
	public static float getAxis(String axisName) {
		return "horizontal".equals(axisName)? horizontal : vertical;
	}
	
	public static void update(float deltaTime) {
		if(horizontalKey == LEFT)
			horizontal -= deltaTime * INCREASE_FACTOR;
		else if(horizontalKey == RIGHT)
			horizontal += deltaTime * INCREASE_FACTOR;
		
		if(verticalKey == UP) 
			vertical -= deltaTime * INCREASE_FACTOR;
		else if(verticalKey == DOWN)
			vertical += deltaTime * INCREASE_FACTOR;
			
//		System.out.println("horizontal: " + horizontal + ", vertical: " + vertical);
	}
	
	public static void setKeyHorizontal(int key) {
		horizontalKey = key;
		if(horizontalKey == NONE) horizontal = 0;
	}
	
	public static void setKeyVertical(int key) {
		verticalKey = key;
		if(verticalKey == NONE) vertical = 0;
	}
}
