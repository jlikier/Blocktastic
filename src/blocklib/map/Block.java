/*
 * Copyright 2010 - James Likier and Julian Hartline
 */
package blocklib.map;

import blocklib.common.*;

public class Block {
	public int typeID; //which blocktype does this map to
	public Vector3F position;
	public Vector3F velocity;
	public int durability; //how many hits left before breaking
	
	public Vector3F getPosition()
	{
		return position;
	}
	public void setPosition(Vector3F p)
	{
		position = p;
	}
	
	public Vector3F getVelocity()
	{
		return velocity;
	}
	public void setVelocity(Vector3F v)
	{
		velocity = v;
	}
	
	public void update()
	{
	}
}
