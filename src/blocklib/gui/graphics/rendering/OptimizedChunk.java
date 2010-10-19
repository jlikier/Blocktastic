package blocklib.gui.graphics.rendering;

import java.util.*;
import blocklib.common.*;
import blocklib.map.*;

public class OptimizedChunk {
	public List<QuadFace> faces;
	
	public OptimizedChunk()
	{
		faces = new ArrayList<QuadFace>();
	}
	public OptimizedChunk(Chunk c, Vector3I offset)
	{
		this();
		generateFaces(c, offset);
	}
	
	public void generateFaces(Chunk c, Vector3I offset)
	{
		Block b, temp;
		Vector3I cursor, test;
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
							faces.add(BlockFace.topFace.offset(offset.add(x, y, z)));
						//bottom
						test = cursor.add(0,-1, 0);
						temp = c.getBlock(test);
						if(!c.validPosition(test) || temp == null)
							faces.add(BlockFace.bottomFace.offset(offset.add(x, y, z)));
						//front
						test = cursor.add(0,0,1);
						temp = c.getBlock(test);
						if(!c.validPosition(test) || temp == null)
							faces.add(BlockFace.frontFace.offset(offset.add(x, y, z)));
						//back 
						test = cursor.add(0,0,-1);
						temp = c.getBlock(test);
						if(!c.validPosition(test) || temp == null)
							faces.add(BlockFace.backFace.offset(offset.add(x, y, z)));
						//left
						test = cursor.add(-1, 0, 0);
						temp = c.getBlock(test);
						if(!c.validPosition(test) || temp == null)
							faces.add(BlockFace.leftFace.offset(offset.add(x, y, z)));
						//right
						test = cursor.add(1, 0, 0);
						temp = c.getBlock(test);
						if(!c.validPosition(test) || temp == null)
							faces.add(BlockFace.rightFace.offset(offset.add(x, y, z)));
					}
				}
			}
		}
	}
}
