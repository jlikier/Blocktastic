/*
 * Copyright 2010 - James Likier and Julian Hartline
 */
package blocklib.map;

import blocklib.common.*;
import blocklib.map.block.*;

public class Chunk {
	public Vector3F chunkSize;
	public Block[][][] blocks;
	
	public Chunk(Vector3F chunkSize)
	{
		//TODO is there a reason that we're cloning this vector? there doesnt seem any reason not to use..
		// this.blocks = new Block[chunkSize.x][chunkSize.y][chunkSize.z];
		// instead of all of this
		Vector3F d = chunkSize.clone();
		int x = (int)d.x;
		int y = (int)d.y;
		int z = (int)d.z;
		
		this.chunkSize = chunkSize;
		this.blocks = new Block[x][y][z];
	}
}
