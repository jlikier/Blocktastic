/*
 * Copyright 2010 - James Likier
 */
package blocklib.map;

import blocklib.common.*;

public class Block implements IEntity{
	public int textureID;
	public Position position;
	public Dimension dimension;
	public Movement movement;
	
	public int getTextureID()
	{
		return textureID;
	}
	public void setTextureID(int tid)
	{
		textureID = tid;
	}
	
	public Position getPosition()
	{
		return position;
	}
	public void setPosition(Position p)
	{
		position = p;
	}
	
	public Dimension getDimension()
	{
		return dimension;
	}
	public void setDimension(Dimension d)
	{
		dimension = d;
	}
	
	public Movement getMovement()
	{
		return movement;
	}
	public void setMovement(Movement m)
	{
		movement = m;
	}
	
	public void Update()
	{
	}
}
