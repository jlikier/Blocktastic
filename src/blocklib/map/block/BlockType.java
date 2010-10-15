/*
 * Copyright 2010 - James Likier and Julian Hartline
 */
package blocklib.map.block;

import blocklib.common.*;

public class BlockType {
	public int typeID;
	public Vector3F dimension;
	public boolean breakable;
	public int maxDurability; //how many hits does it take to break this block
	public float regenRate; //the durability regen rate
	public int textureID;
	public float friction;
	public boolean collidable;
	public boolean transparent;

}
