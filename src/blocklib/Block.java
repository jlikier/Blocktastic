/*
 * Copyright 2010 - James Likier
 */
package blocklib;

import blocklib.common.*;

public class Block implements IEntity{
	public Position position;
	public Dimension dimension;
	public Movement movement;
	
	public Position gPosition()
	{
		return position;
	}
	public void sPosition(Position p)
	{
		position = p;
	}
	
	public Dimension gDimension()
	{
		return dimension;
	}
	public void sDimension(Dimension d)
	{
		dimension = d;
	}
	
	public Movement gMovement()
	{
		return movement;
	}
	public void sMovement(Movement m)
	{
		movement = m;
	}
	
	public void Update()
	{
	}
}
