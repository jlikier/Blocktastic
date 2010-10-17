/*
 * Copyright 2010 - James Likier and Julian Hartline
 */
package blocklib.map;

import blocklib.common.*;
import blocklib.map.block.*;

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
		//int x = chunkSize.x % ((v.x == 0) ? chunkSize.x : v.x);
		//int y = chunkSize.y % ((v.y == 0) ? chunkSize.y : v.y);
		//int z = chunkSize.z % ((v.z == 0) ? chunkSize.z : v.z);
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
}
