/*
 * Copyright 2010 - James Likier and Julian Hartline
 */
package blocklib.map;

import blocklib.common.*;
import blocklib.map.block.*;

public class Map {
	public Vector3I mapSize;
	public Vector3I chunkSize;
	public Chunk[][][] chunks;
	public String name;
	
	public Map(Vector3I mapSize, Vector3I chunkSize)
	{
		this(mapSize, chunkSize, "DEFAULT");
	}
	public Map(Vector3I mapSize, Vector3I chunkSize, String name)
	{
		this.mapSize = mapSize.clone();
		this.chunkSize = chunkSize.clone();
		this.chunks = new Chunk[mapSize.x][mapSize.y][mapSize.z];
		this.name = name;
	}
	
	public Chunk getChunk(Vector3I v)
	{
		Chunk c = null;
		
		int x = mapSize.x / v.x;
		int y = mapSize.y / v.y;
		int z = mapSize.z / v.z;
		
		// If the position is valid, get the chunk
		if(validPosition(x,y,z))
		{
			c = chunks[x][y][z];
		}
		
		return c;
	}
	public void setChunk(Vector3I v, Chunk c)
	{
		int x = mapSize.x / v.x;
		int y = mapSize.y / v.y;
		int z = mapSize.z / v.z;
		// If the position is valid, set the chunk
		if(validPosition(x,y,z))
		{
			chunks[v.x][v.y][v.z] = c;
		}
	}
	
	public Block getBlock(Vector3I v)
	{
		Block b = null;
		Chunk c = getChunk(v);
		
		if(c!= null)
		{
			// Get block
			b = c.getBlock(v);
		}
		
		return b;
	}
	public void setBlock(Vector3I v, Block b)
	{
		Chunk c = getChunk(v);
		
		// Create the chunk, if it doesn't exist
		if(c==null)
		{
			// Create chunk
			c = new Chunk(chunkSize);
			setChunk(v, c);
		}
		
		// Set the block
		c.setBlock(v, b);
	}
	
	public boolean validPosition(Vector3I v)
	{
		return validPosition(v.x, v.y, v.z);
	}
	public boolean validPosition(int x, int y, int z)
	{
		if((x < 0 || x >= mapSize.x)
			|| (y < 0 || y >= mapSize.y)
			|| (z < 0 || z >= mapSize.z))
		{
			return false;
		}
		return true;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Vector3I getMapSize() {
		return mapSize;
	}
	public Vector3I getChunkSize() {
		return chunkSize;
	}
}
