package blocklib.gui.graphics;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import java.util.Random;

import blocklib.common.*;
import blocklib.map.*;
import blocklib.map.block.*;
import testing.ChunkOptimization;

import static org.lwjgl.opengl.GL11.*;

public class VertexArrayTest {
	FloatBuffer vertices;
	Chunk c;
	ChunkOptimization co;
	FloatBuffer batch;
	Random r;
	
	public VertexArrayTest()
	{
		r = new Random();
		CreateVertexArray();
		CreateChunk();
		batch = BufferUtils.createFloatBuffer(8*8*8*3*4*6*16);
	}
	public void renderChunkOptimization()
	{
		/*
		int count = 0;
		for(int x = 0; x < 64; x++)
		{
			count = 0;
			batch.clear();
			for(int y = 0; y < 16; y++)
			{
				batch.put(co.vertices);
				count += co.blockCount;
			}
			batch.flip();
			//long s = System.nanoTime();
			renderVertexArray(batch, count * 3 * 4 * 6);
			//long e = System.nanoTime();
			//System.out.println(e - s);
		}
		*/
		renderChunkOptimization(co);
	}
	public void renderChunkOptimization(ChunkOptimization co)
	{
		long s, e;
		int total = 0;
		//s = System.nanoTime();
		glPushMatrix();{
			for(int x = 0; x < 64; x++)
			{
				glTranslatef(8,0,0);
				glPushMatrix();{
					for(int y = 0; y < 16; y++)
					{
						glTranslatef(0,8,0);
						glEnableClientState(GL_VERTEX_ARRAY);
						glVertexPointer(3, 0, co.vertices);
						glDrawArrays(GL_QUADS, 0, co.blockCount * 3 * 4 * 6);
						glDisableClientState(GL_VERTEX_ARRAY);
						total += co.blockCount;
					}
				}glPopMatrix();
			}
		}glPopMatrix();
		//e = System.nanoTime();
		//System.out.println((e-s));
		//System.out.println((e-s) / 1000000f);
		//System.out.println("Rendered "+total+" fully drawn cubes.");
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
	public void renderVertexArray(FloatBuffer buffer, int count)
	{
		glEnableClientState(GL_VERTEX_ARRAY);
		glVertexPointer(3,0,buffer);
		glDrawArrays(GL_QUADS, 0, count);
		glDisableClientState(GL_VERTEX_ARRAY);
	}
	public void CreateChunk()
	{
		c = new Chunk(new Vector3I(8,8,8));
		Vector3I v;
		
		for(int i = 0; i < 64; i++)
		{
			v = new Vector3I(r.nextInt(7), r.nextInt(7), r.nextInt(7));
			c.setBlock(v, new Block());
		}
		
		co = new ChunkOptimization(c.chunkSize);
		co.CalculateChunk(c);
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
