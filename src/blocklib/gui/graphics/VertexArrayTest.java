package blocklib.gui.graphics;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;

public class VertexArrayTest {
	FloatBuffer vertices;
	
	public VertexArrayTest()
	{
		CreateVertexArray();
	}
	public void renderMoarCubes()
	{
		glPushMatrix();{
			glTranslatef(-50,-50,0);
			for(int x = 0; x < 100; x=x+5)
			{
				for(int y = 0; y < 100; y++)
				{
					glPushMatrix();{
						glTranslatef(x,y,0);
						renderVertexArray();
					}glPopMatrix();
				}
			}
		}glPopMatrix();
	}
	public void renderVertexArray()
	{
		glEnableClientState(GL_VERTEX_ARRAY);
		glVertexPointer(3, 0, vertices);
		glDrawArrays(GL_QUADS, 0, 12 * 5);
		glDisableClientState(GL_VERTEX_ARRAY);
	}
	public void CreateVertexArray()
	{
		vertices = BufferUtils.createFloatBuffer(3 * 4 * 6 * 5);
		vertices.put(new float[] {
				-1.0f, -1.0f, 1.0f,
				1.0f, -1.0f, 1.0f,
				1.0f, 1.0f, 1.0f,
				-1.0f, 1.0f, 1.0f,

				-1.0f, -1.0f, -1.0f,
				-1.0f, 1.0f, -1.0f,
				1.0f, 1.0f, -1.0f,
				1.0f, -1.0f, -1.0f,

				-1.0f, 1.0f, -1.0f,
				-1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, -1.0f,

				-1.0f, -1.0f, -1.0f,
				1.0f, -1.0f, -1.0f,
				1.0f, -1.0f, 1.0f,
				-1.0f, -1.0f, 1.0f,

				1.0f, -1.0f, -1.0f,
				1.0f, 1.0f, -1.0f,
				1.0f, 1.0f, 1.0f,
				1.0f, -1.0f, 1.0f,

				-1.0f, -1.0f, -1.0f,
				-1.0f, -1.0f, 1.0f,
				-1.0f, 1.0f, 1.0f,
				-1.0f, 1.0f, -1.0f,

				//cube 2
				1.0f, -1.0f, 1.0f,
				3.0f, -1.0f, 1.0f,
				3.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 1.0f,
				
				1.0f, -1.0f, -1.0f,
				1.0f, 1.0f, -1.0f,
				3.0f, 1.0f, -1.0f,
				3.0f, -1.0f, -1.0f,
				
				1.0f, 1.0f, -1.0f,
				1.0f, 1.0f, 1.0f,
				3.0f, 1.0f, 1.0f,
				3.0f, 1.0f, -1.0f,
				
				1.0f, -1.0f, -1.0f,
				3.0f, -1.0f, -1.0f,
				3.0f, -1.0f, 1.0f,
				1.0f, -1.0f, 1.0f,
				
				3.0f, -1.0f, -1.0f,
				3.0f, 1.0f, -1.0f,
				3.0f, 1.0f, 1.0f,
				1.0f, -1.0f, 1.0f,
				
				1.0f, -1.0f, -1.0f,
				1.0f, -1.0f, 1.0f,
				1.0f, 1.0f, 1.0f,
				1.0f, 1.0f, -1.0f,
				
				//cube 3
				3.0f, -1.0f, 1.0f,
				5.0f, -1.0f, 1.0f,
				5.0f, 1.0f, 1.0f,
				3.0f, 1.0f, 1.0f,
				
				3.0f, -1.0f, -1.0f,
				3.0f, 1.0f, -1.0f,
				5.0f, 1.0f, -1.0f,
				5.0f, -1.0f, -1.0f,
				
				3.0f, 1.0f, -1.0f,
				3.0f, 1.0f, 1.0f,
				5.0f, 1.0f, 1.0f,
				5.0f, 1.0f, -1.0f,
				
				3.0f, -1.0f, -1.0f,
				5.0f, -1.0f, -1.0f,
				5.0f, -1.0f, 1.0f,
				3.0f, -1.0f, 1.0f,
				
				5.0f, -1.0f, -1.0f,
				5.0f, 1.0f, -1.0f,
				5.0f, 1.0f, 1.0f,
				3.0f, -1.0f, 1.0f,
				
				3.0f, -1.0f, -1.0f,
				3.0f, -1.0f, 1.0f,
				3.0f, 1.0f, 1.0f,
				3.0f, 1.0f, -1.0f,
				
				// cube 4
				5.0f, -1.0f, 1.0f,
				7.0f, -1.0f, 1.0f,
				7.0f, 1.0f, 1.0f,
				5.0f, 1.0f, 1.0f,
				
				5.0f, -1.0f, -1.0f,
				5.0f, 1.0f, -1.0f,
				7.0f, 1.0f, -1.0f,
				7.0f, -1.0f, -1.0f,
				
				5.0f, 1.0f, -1.0f,
				5.0f, 1.0f, 1.0f,
				7.0f, 1.0f, 1.0f,
				7.0f, 1.0f, -1.0f,
				
				5.0f, -1.0f, -1.0f,
				7.0f, -1.0f, -1.0f,
				7.0f, -1.0f, 1.0f,
				5.0f, -1.0f, 1.0f,
				
				7.0f, -1.0f, -1.0f,
				7.0f, 1.0f, -1.0f,
				7.0f, 1.0f, 1.0f,
				5.0f, -1.0f, 1.0f,
				
				5.0f, -1.0f, -1.0f,
				5.0f, -1.0f, 1.0f,
				5.0f, 1.0f, 1.0f,
				5.0f, 1.0f, -1.0f,
				
				//cube 5
				5.0f, -1.0f, 1.0f,
				7.0f, -1.0f, 1.0f,
				7.0f, 1.0f, 1.0f,
				5.0f, 1.0f, 1.0f,
				
				5.0f, -1.0f, -1.0f,
				5.0f, 1.0f, -1.0f,
				7.0f, 1.0f, -1.0f,
				7.0f, -1.0f, -1.0f,
				
				5.0f, 1.0f, -1.0f,
				5.0f, 1.0f, 1.0f,
				7.0f, 1.0f, 1.0f,
				7.0f, 1.0f, -1.0f,
				
				5.0f, -1.0f, -1.0f,
				7.0f, -1.0f, -1.0f,
				7.0f, -1.0f, 1.0f,
				5.0f, -1.0f, 1.0f,
				
				7.0f, -1.0f, -1.0f,
				7.0f, 1.0f, -1.0f,
				7.0f, 1.0f, 1.0f,
				5.0f, -1.0f, 1.0f,
				
				5.0f, -1.0f, -1.0f,
				5.0f, -1.0f, 1.0f,
				5.0f, 1.0f, 1.0f,
				5.0f, 1.0f, -1.0f
		});
		vertices.flip();
	}
}
