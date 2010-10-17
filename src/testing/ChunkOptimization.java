package testing;

import java.nio.FloatBuffer;

import blocklib.common.*;
import blocklib.map.*;
import blocklib.map.block.*;

public class ChunkOptimization {
	public int blockCount;
	public FloatBuffer vertices;
	
	public ChunkOptimization(Vector3I v)
	{
		this(v.x, v.y, v.z);
	}
	public ChunkOptimization(int x, int y, int z)
	{
		vertices = FloatBuffer.allocate(x*y*z*3);
		blockCount = 0;
	}
	
	public void CalculateChunk(Chunk c)
	{
		Block top, bottom, front, back, left, right, cBlock;
		Vector3I cursor = new Vector3I(0,0,0);
		Vector3I offset;
		blockCount = 0;
		vertices.clear();
		
		for(int x = 0; x < c.chunkSize.x; x++)
		{
			for(int y = 0; y < c.chunkSize.y; y++)
			{
				for(int z = 0; z < c.chunkSize.z; z++)
				{
					cursor = new Vector3I(x,y,z);
					cBlock = c.getBlock(cursor);
					if(cBlock != null)
					{
						//top
						offset = cursor.add(0, 1, 0);
						top = c.getBlock(offset);
						//bottom
						offset = cursor.add(0, -1, 0);
						bottom = c.getBlock(offset);
						//front
						offset = cursor.add(0,0,-1);
						front = c.getBlock(offset);
						//back
						offset = cursor.add(0,0,1);
						back = c.getBlock(offset);
						//left
						offset = cursor.add(-1, 0, 0);
						left = c.getBlock(offset);
						//right
						offset = cursor.add(1,0,0);
						right = c.getBlock(offset);

						if(isVisible(top,bottom,front,back,left,right))
						{
							blockCount++;
							AddVertices(cursor);
						}
					}
				}
			}
		}
		
		vertices.flip();
		
	}
	
	public void AddVertices(Vector3I v)
	{
		// 3 floats per vector, 4 vectors per face, 6 faces per block
		
		//front
		vertices.put(new float[] {-1 + v.x, 1 + v.y, -1 + v.z});
		vertices.put(new float[] {-1 + v.x, -1 + v.y, -1 + v.z});
		vertices.put(new float[] {1 + v.x, -1 + v.y, -1 + v.z});
		vertices.put(new float[] {1 + v.x, 1 + v.y, -1 + v.z});
		
		//back
		vertices.put(new float[] {1 + v.x, 1 + v.y, 1 + v.z});
		vertices.put(new float[] {1 + v.x, -1 + v.y, 1 + v.z});
		vertices.put(new float[] {-1 + v.x, -1 + v.y, 1 + v.z});
		vertices.put(new float[] {-1 + v.x, 1 + v.y, 1 + v.z});
		
		//top
		vertices.put(new float[] {});
		
		//bottom
		vertices.put(new float[] {});
		
		//left
		vertices.put(new float[] {});
		
		//right
		vertices.put(new float[] {});
		
	}
	
	public static boolean isVisible(Block top,
						  Block bottom,
						  Block front,
						  Block back,
						  Block left,
						  Block right)
	{
		if(top == null)
			return true;
		if(bottom == null)
			return true;
		if(front == null)
			return true;
		if(back == null)
			return true;
		if(left == null)
			return true;
		if(right == null)
			return true;
		return false;
	}
}
