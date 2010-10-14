/*
 * Copyright 2010 - James Likier and Julian Hartline
 */
package blocklib.common;

public class Vector3F {
	public float x;
	public float y;
	public float z;
	
	public Vector3F(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3F clone()
	{
		return new Vector3F(x,y,z);
	}
}
