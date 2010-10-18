package testing;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL11.*;
import java.nio.*;

public class VBOTest {
	public int vboid;
	public FloatBuffer vertices;
	public int size;
	public float[] cube = {
			-0.5f, 0.5f, 0.5f,
			-0.5f, -0.5f, 0.5f,
			0.5f, -0.5f, 0.5f,
			0.5f, 0.5f, 0.5f,

			0.5f, 0.5f, -0.5f,
			0.5f, -0.5f, -0.5f,
			-0.5f, -0.5f, -0.5f,
			-0.5f, 0.5f, -0.5f,

			-0.5f, 0.5f, -0.5f,
			-0.5f, 0.5f, 0.5f,
			0.5f, 0.5f, 0.5f,
			0.5f, 0.5f, -0.5f,

			0.5f, -0.5f, -0.5f,
			0.5f, -0.5f, 0.5f,
			-0.5f, -0.5f, 0.5f,
			-0.5f, -0.5f, -0.5f,

			-0.5f, 0.5f, -0.5f,
			-0.5f, -0.5f, -0.5f,
			-0.5f, -0.5f, 0.5f,
			-0.5f, 0.5f, 0.5f,

			0.5f, 0.5f, 0.5f,
			0.5f, -0.5f, 0.5f,
			0.5f, -0.5f, -0.5f,
			0.5f, 0.5f, -0.5f
	};
	
	public VBOTest()
	{
		vboid = 0;
		vertices = BufferUtils.createFloatBuffer(cube.length);
		vertices.put(cube);
		vertices.flip();
	}
	
	public void render()
	{
		ARBVertexBufferObject.glBindBufferARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, vboid);
		glEnableClientState(GL_VERTEX_ARRAY);
		glVertexPointer(3, GL_FLOAT, 0, 0);
		glDrawArrays(GL_QUADS, 0, size);
		glDisableClientState(GL_VERTEX_ARRAY);
	}
	
	public void BufferData(FloatBuffer b, int size)
	{
		ARBVertexBufferObject.glBindBufferARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, vboid);
		ARBVertexBufferObject.glBufferDataARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, b, ARBVertexBufferObject.GL_DYNAMIC_DRAW_ARB);
		this.size = size;
	}
	
	public void GenerateBuffer()
	{
		vboid = ARBVertexBufferObject.glGenBuffersARB();
		System.out.println("VBO ID: "+vboid);
		
		ARBVertexBufferObject.glBindBufferARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, vboid);
		System.out.println("Bound VBO");
		
		ARBVertexBufferObject.glBufferDataARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, vertices, ARBVertexBufferObject.GL_DYNAMIC_DRAW_ARB);
		size = cube.length;
		System.out.println("Buffered Data in VBO");
	}
}
