/*
 * Copyright 2010 - James Likier
 */
package blocklib.map;

import blocklib.common.*;

public class Block implements IEntity{
	public int textureID;
	public Vector3F position;
	public Vector3F dimension;
	public Vector3F velocity;
	
	public int getTextureID()
	{
		return textureID;
	}
	public void setTextureID(int tid)
	{
		textureID = tid;
	}
	
	public Vector3F getPosition()
	{
		return position;
	}
	public void setPosition(Vector3F p)
	{
		position = p;
	}
	
	public Vector3F getDimension()
	{
		return dimension;
	}
	public void setDimension(Vector3F d)
	{
		dimension = d;
	}
	
	public Vector3F getVelocity()
	{
		return velocity;
	}
	public void setVelocity(Vector3F v)
	{
		velocity = v;
	}
	
	public void Update()
	{
	}
}
