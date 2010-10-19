package blocklib.gui.graphics.rendering;

import blocklib.common.*;
import blocklib.map.*;
import java.util.*;
import java.nio.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL11.*;

public class MapRenderer {
	public HashMap<String, OptimizedChunk> chunks;
	public FloatBuffer vertices;
	public int verticesSize;
	public Vector3I chunkSize;
	public int chunksToBatch;
	public int vboid;
	
	public MapRenderer(Vector3I chunkSize)
	{
		this.chunksToBatch = 16 * 16;
		this.chunkSize = chunkSize.clone();
		this.verticesSize = chunkSize.x * chunkSize.y * chunkSize.z * chunksToBatch * 3 * 4 * 6;
		this.vertices = BufferUtils.createFloatBuffer(verticesSize);
		this.chunks = new HashMap<String, OptimizedChunk>();
		this.vboid = -1;
		createVBO();
	}
	
	public void createVBO()
	{
		vboid = ARBVertexBufferObject.glGenBuffersARB();
	}
	
	public void addChunk(Chunk chunk, Vector3I position)
	{
		chunks.put(position.toString(), new OptimizedChunk(chunk, position));
	}
	public void removeChunk(Vector3I position)
	{
		chunks.remove(position.toString());
	}
	
	public void render()
	{
		if(vboid>0)
		{
			int count = 0;
			int faces = 0;
			
			// Clear the buffer
			vertices.clear();
			
			// Fill the vbo with data
			for(OptimizedChunk c : chunks.values())
			{
				count++;
				
				for(QuadFace f : c.faces)
				{
					faces++;
					//System.out.println("Face");
					//System.out.println(f.v1);
					//System.out.println(f.v2);
					//System.out.println(f.v3);
					//System.out.println(f.v4);
					//System.out.println("End Face");
					vertices.put(f.v1.x);
					vertices.put(f.v1.y);
					vertices.put(f.v1.z);
					vertices.put(f.v2.x);
					vertices.put(f.v2.y);
					vertices.put(f.v2.z);
					vertices.put(f.v3.x);
					vertices.put(f.v3.y);
					vertices.put(f.v3.z);
					vertices.put(f.v4.x);
					vertices.put(f.v4.y);
					vertices.put(f.v4.z);
				}
				
				if(count > chunksToBatch)
				{
					// We have our buffer ready for rendering
					vertices.flip();
					renderVBO(faces * 3 * 4);
					vertices.clear();
					count = 0;
					faces = 0;
				}
			}
			if(count > 0)
			{
				// We have our buffer ready for rendering
				vertices.flip();
				renderVBO(faces * 3 * 4);
			}
		}
	}
	public void renderVBO(int size)
	{
		ARBVertexBufferObject.glBindBufferARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, vboid);
		ARBVertexBufferObject.glBufferDataARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, vertices, ARBVertexBufferObject.GL_DYNAMIC_DRAW_ARB);
		glEnableClientState(GL_VERTEX_ARRAY);
		glVertexPointer(3, GL_FLOAT, 0, 0);
		glDrawArrays(GL_QUADS, 0, size);
		glDisableClientState(GL_VERTEX_ARRAY);
	}
}
