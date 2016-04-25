package Utils;

import static org.lwjgl.glfw.GLFW.*;

public class Input {
	
	private long window;
	private boolean[] keys;
	
	public Input(long window) {
		this.window = window;
		keys = new boolean[GLFW_KEY_LAST];
		
	}
	public boolean isKeyDown(int key) {
		return glfwGetKey(window, key) == 1;
	}
	public boolean isMouseButton(int button) {
		return glfwGetMouseButton(window, button) == 1;
	}
	public void update() {
		for(int i = 0; i< keys.length; i++) {
			keys[i] = isKeyDown(i);
		}
	}
	public boolean isKeyPressed(int key) {
		return (isKeyDown(key) && !keys[key]);
	}
	public boolean isKeyReleased(int key) {
		return (!isKeyDown(key) && keys[key]);
	}

}
