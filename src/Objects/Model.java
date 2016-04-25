package Objects;

import java.util.List;

import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
import java.nio.FloatBuffer;

public class Model {
	private float[] vertices;
	private float[] normals;
	private float[] uvs;
	private int vaoID, verticesID, normalsID, uvsID;

	public Model() {
	}

	public Model(List<Float> vertices, List<Float> normals, List<Float> uvs) {
		// make the list from the loader to float arrays
		float[] verticesTemp = new float[vertices.size()];
		float[] normalsTemp = new float[normals.size()];
		float[] uvsTemp = new float[uvs.size()];
		for (int i = 0; i < vertices.size(); i++) {
			verticesTemp[i] = vertices.get(i);
		}
		for (int i = 0; i < normals.size(); i++) {
			normalsTemp[i] = normals.get(i);
		}
		for (int i = 0; i < uvs.size(); i++) {
			uvsTemp[i] = uvs.get(i);
		}
		this.vertices = verticesTemp;
		this.normals = normalsTemp;
		this.uvs = uvsTemp;
		bindAttributes();
	}

    public Model(List<Float> vertices) {
        float[] verticesTemp = new float[vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            verticesTemp[i] = vertices.get(i);
        }
        this.vertices = verticesTemp;
    }

	private void bindAttributes() {

		// Create and bind a VAO
		this.vaoID = glGenVertexArrays();
		glBindVertexArray(this.vaoID);

		// Create and bind the vertices
		if (vertices.length > 0) {
			verticesID = glGenBuffers();
			glBindBuffer(GL_ARRAY_BUFFER, verticesID);

			// Buffer the data to the vbo
			FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(vertices.length);
			verticesBuffer.put(vertices).flip();
			glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
		} else {
			System.err.println("no vertices were found!");
			;
			return;
		}

		// Create and buffer the normals if there is some in the array
		if (normals.length > 0) {
			normalsID = glGenBuffers();
			glBindBuffer(GL_ARRAY_BUFFER, normalsID);

			// Buffer the data to the vbo
			FloatBuffer normalsBuffer = BufferUtils.createFloatBuffer(normals.length);
			normalsBuffer.put(normals).flip();
			glBufferData(GL_ARRAY_BUFFER, normalsBuffer, GL_STATIC_DRAW);
		} else {
			System.err.println("no normals were found!");
			;
		}

		// Create and buffer the UVS if there is some in the array
		if (uvs.length > 0) {
			uvsID = glGenBuffers();
			glBindBuffer(GL_ARRAY_BUFFER, uvsID);

			// Buffer the data to the vbo
			FloatBuffer uvBuffer = BufferUtils.createFloatBuffer(uvs.length);
			uvBuffer.put(uvs).flip();
			glBufferData(GL_ARRAY_BUFFER, uvBuffer, GL_STATIC_DRAW);
		} else {
			System.out.println("No UVs were found for " + this);
		}

		// Unbind vao and vbo
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}

	public float[] getVertices() {
		return vertices;
	}

	public void setVertices(float[] vertices) {
		this.vertices = vertices;
	}

	public float[] getNormals() {
		return normals;
	}

	public void setNormals(float[] normals) {
		this.normals = normals;
	}

	public float[] getUvs() {
		return uvs;
	}

	public void setUvs(float[] uvs) {
		this.uvs = uvs;
	}

	public int getVaoID() {
		return vaoID;
	}

	public void setVaoID(int vaoID) {
		this.vaoID = vaoID;
	}

	public int getVerticesID() {
		return verticesID;
	}

	public void setVerticesID(int verticesID) {
		this.verticesID = verticesID;
	}

	public int getNormalsID() {
		return normalsID;
	}

	public void setNormalsID(int normalsID) {
		this.normalsID = normalsID;
	}

	public int getUvsID() {
		return uvsID;
	}

	public void setUvsID(int uvsID) {
		this.uvsID = uvsID;
	}
}
