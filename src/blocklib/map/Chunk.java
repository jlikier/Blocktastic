/*
 * Copyright 2010 - James Likier and Julian Hartline
 */
package blocklib.map;

import blocklib.common.*;
import java.util.Random;

public class Chunk {
	public Vector3I chunkSize;
	public Block[][][] blocks;
	
	public Chunk(Vector3I chunkSize)
	{
		this.chunkSize = chunkSize.clone();
		this.blocks = new Block[chunkSize.x][chunkSize.y][chunkSize.z];
	}
	
	public Block getBlock(Vector3I v)
	{
		Block b = null;
		
		int x = v.x % chunkSize.x;
		int y = v.y % chunkSize.y;
		int z = v.z % chunkSize.z;
		
		if(validPosition(x,y,z))
		{
			b = blocks[x][y][z];
		}
		
		return b;
	}
	public void setBlock(Vector3I v, Block b)
	{
		int x = v.x % chunkSize.x;
		int y = v.y % chunkSize.y;
		int z = v.z % chunkSize.z;
		
		if(validPosition(x,y,z))
		{
			blocks[x][y][z] = b;
		}
	}
	
	public boolean validPosition(Vector3I v)
	{
		return validPosition(v.x, v.y, v.z);
	}
	public boolean validPosition(int x, int y, int z)
	{
		if((x < 0 || x >= chunkSize.x)
			|| (y < 0 || y >= chunkSize.y)
			|| (z < 0 || z >= chunkSize.z))
		{
			return false;
		}
		return true;
	}
	
	public static Chunk genRandomChunk(Vector3I chunkSize)
	{
		Chunk c = new Chunk(chunkSize);
		Random r = new Random();
		
		for(int x = 0; x < chunkSize.x; x++)
		{
			for(int y = 0; y < chunkSize.y; y++)
			{
				for(int z = 0; z < chunkSize.z; z++)
				{
					//c.setBlock(new Vector3I(r.nextInt(chunkSize.x), r.nextInt(chunkSize.y), r.nextInt(chunkSize.z)), new Block());
					c.setBlock(new Vector3I(x,y,z), new Block());
				}
			}
		}

		return c;
	}
}
