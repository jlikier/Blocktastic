/*
 * Copyright 2010 - James Likier and Julian Hartline
 */
package blocklib.map;

import blocklib.common.*;
import blocklib.map.block.*;

public class Chunk {
	public Vector3F dimension;
	
	public Block[][][] blocks;
	
	public Chunk(Vector3F dimension)
	{
		Vector3F d = dimension.clone();
		int x = (int)d.x;
		int y = (int)d.y;
		int z = (int)d.z;
		
		this.dimension = d;
		this.blocks = new Block[x][y][z];
	}
}
