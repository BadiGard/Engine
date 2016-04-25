package Test;


import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;

import org.lwjgl.opengl.GL;

import Game.Window;
import Loaders.modelLoader;
import Objects.Camera;
import Objects.Model;
import Renderers.Renderer;

public class Main {

	public static void main(String[] args) {
		Window.setCallBacks();
		if(glfwInit() != 1) {
			System.err.println("GLFW window failed to initialize");
			System.exit(1);
		}
		Model cube;
		cube = modelLoader.loadOBJModel("././res/cube.obj");
		Window window = new Window(800,600, "Test")	;
		window.createWindow();
		GL.createCapabilities();
		Renderer renderer = new Renderer(1);
		Camera cam = new Camera(800, 600);
		glEnable(GL_TEXTURE_2D);

		while(!window.shouldClose()) {
			window.update();
			glClear(GL_COLOR_BUFFER_BIT);
			renderer.renderModel(cube);
			window.swapBuffers();
		}
		
	}

}
