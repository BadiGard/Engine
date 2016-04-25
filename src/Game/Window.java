package Game;
import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;

import Utils.Input;
public class Window {
	private long window;
	private int width, height;
	private boolean fullscreen;
	
	private Input i;
	private String name;
	
	public Window(int width, int height, String name) {
		setSize(width, height);
		this.name = name;
		setFullscreen(false);

	}
	
	public void createWindow() {
		window = glfwCreateWindow(width, height, name, fullscreen ? glfwGetPrimaryMonitor() : 0, 0);
		if(window==0) {
			throw new IllegalStateException("Failed to create window");
		}
		if(!fullscreen) {
		GLFWVidMode vid = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (vid.width()-width)/2, (vid.height()-height)/2);
		glfwShowWindow(window);
		} 
		glfwMakeContextCurrent(window);
		i = new Input(window);
	}
	
	public static void setCallBacks() {
		 glfwSetErrorCallback(new GLFWErrorCallback() {
			public void invoke(int errorCode, long description) {
				throw new IllegalStateException(GLFWErrorCallback.getDescription(description));
				
			}
		});
	}
	
	public void update() {
		i.update();
		glfwPollEvents();
	}
	
	public boolean shouldClose() {
		return glfwWindowShouldClose(window) != 0;
	}
	public void swapBuffers() {
		glfwSwapBuffers(window);

	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	public boolean isFullscreen() {
		return fullscreen;
	}

	public void setFullscreen(boolean fullscreen) {
		this.fullscreen = fullscreen;
	}
	
	public long getWindow() {
		return window;
	}
	public Input getInput() { 
		return i;
	}
}