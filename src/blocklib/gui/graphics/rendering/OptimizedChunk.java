package blocklib.gui.graphics.rendering;

import java.util.*;
import blocklib.common.*;
import blocklib.map.*;
import java.util.*;
import java.nio.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL11.*;

public class OptimizedChunk {
	public List<QuadFace> faces;
	public int vboid;
	public FloatBuffer vertices;
	public int verticecount;
	
	public OptimizedChunk()
	{
		faces = new ArrayList<QuadFace>();
		vboid = ARBVertexBufferObject.glGenBuffersARB();
	}
	public OptimizedChunk(Chunk c, Vector3I offset)
	{
		this();
		vertices = BufferUtils.createFloatBuffer(c.chunkSize.x * c.chunkSize.y * c.chunkSize.z * 3 * 4 * 6);
		verticecount = 0;
		generateFaces(c, offset);
	}
	public void render()
	{
		ARBVertexBufferObject.glBindBufferARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, vboid);
		glEnableClientState(GL_VERTEX_ARRAY);
		glVertexPointer(3, GL_FLOAT, 0, 0);
		glDrawArrays(GL_QUADS, 0, verticecount * 3);
		glDisableClientState(GL_VERTEX_ARRAY);
	}
	public void bufferData()
	{
		ARBVertexBufferObject.glBindBufferARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, vboid);
		ARBVertexBufferObject.glBufferDataARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, vertices, ARBVertexBufferObject.GL_DYNAMIC_DRAW_ARB);
	}
	public void putVertice(Vector3F v)
	{
		vertices.put(v.x);
		vertices.put(v.y);
		vertices.put(v.z);
	}
	
	public void generateFaces(Chunk c, Vector3I offset)
	{
		Block b, temp;
		Vector3I cursor, test;
		QuadFace f;
		for(int x = 0; x < c.chunkSize.x; x++)
		{
			for(int y = 0; y < c.chunkSize.y; y++)
			{
				for(int z = 0; z < c.chunkSize.z; z++)
				{
					cursor = new Vector3I(x,y,z);
					b = c.getBlock(cursor);
					
					if(b != null)
					{
						//top
						test = cursor.add(0,1,0);
						temp = c.getBlock(cursor);
						if(!c.validPosition(test) || temp == null)
						{
							f = BlockFace.topFace.offset(offset.add(x, y, z));
							putVertice(f.v1);
							putVertice(f.v2);
							putVertice(f.v3);
							putVertice(f.v4);
							verticecount+=4;
						}
						//bottom
						test = cursor.add(0,-1, 0);
						temp = c.getBlock(test);
						if(!c.validPosition(test) || temp == null)
						{
							f = BlockFace.bottomFace.offset(offset.add(x, y, z));
							putVertice(f.v1);
							putVertice(f.v2);
							putVertice(f.v3);
							putVertice(f.v4);
							verticecount+=4;
						}
						//front
						test = cursor.add(0,0,1);
						temp = c.getBlock(test);
						if(!c.validPosition(test) || temp == null)
						{
							f = BlockFace.frontFace.offset(offset.add(x, y, z));
							putVertice(f.v1);
							putVertice(f.v2);
							putVertice(f.v3);
							putVertice(f.v4);
							verticecount+=4;
						}
						//back 
						test = cursor.add(0,0,-1);
						temp = c.getBlock(test);
						if(!c.validPosition(test) || temp == null)
						{
							f = BlockFace.backFace.offset(offset.add(x, y, z));
							putVertice(f.v1);
							putVertice(f.v2);
							putVertice(f.v3);
							putVertice(f.v4);
							verticecount+=4;
						}
						//left
						test = cursor.add(-1, 0, 0);
						temp = c.getBlock(test);
						if(!c.validPosition(test) || temp == null)
						{
							f = BlockFace.leftFace.offset(offset.add(x, y, z));
							putVertice(f.v1);
							putVertice(f.v2);
							putVertice(f.v3);
							putVertice(f.v4);
							verticecount+=4;
						}
						//right
						test = cursor.add(1, 0, 0);
						temp = c.getBlock(test);
						if(!c.validPosition(test) || temp == null)
						{
							f = BlockFace.rightFace.offset(offset.add(x, y, z));
							putVertice(f.v1);
							putVertice(f.v2);
							putVertice(f.v3);
							putVertice(f.v4);
							verticecount+=4;
						}
					}
				}
			}
		}
		vertices.flip();
		bufferData();
	}
}
