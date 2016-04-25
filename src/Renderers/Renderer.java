package Renderers;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import Objects.Model;

public class Renderer {
	private int program;
	public Renderer(int program) {
		this.program = program;
	}
	private void bindModel(Model model) {
		glBindVertexArray(model.getVaoID());
		//make sure that vertices exist
		if(model.getVertices().length > 0) {
			glBindBuffer(GL_ARRAY_BUFFER, model.getVerticesID());
			glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
            glEnableVertexAttribArray(0);
		}
		if(model.getNormals().length > 0) {
			glBindBuffer(GL_ARRAY_BUFFER, model.getNormalsID());
			glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);
            glEnableVertexAttribArray(1);
		}
		if(model.getUvs().length > 0) {
			glBindBuffer(GL_ARRAY_BUFFER, model.getUvsID());
			glVertexAttribPointer(2, 3, GL_FLOAT, false, 0, 0);
            glEnableVertexAttribArray(2);
		}
		
		
	}
	private void unbindModel() {
		glDisableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
	}
	public void renderModel(Model model) {

        bindModel(model);
        glDrawArrays(GL_TRIANGLES, 0, model.getVertices().length);
        unbindModel();
    }


}
