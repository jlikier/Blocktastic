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

	public float getMagnitude()
	{
		return (float)Math.cbrt(x*x + y*y + z*z);
	}

	public Vector3F add(Vector3F v)
	{
		return new Vector3F(x+v.x, y+v.y, z+v.z);
	}

	public Vector3F scale(float factor)
	{
		return new Vector3F(x*factor, y*factor, z*factor);
	}

	// Returns a unit vector in the same direction
	public Vector3F getUnitVector()
	{
		return this.scale(1.0F / getMagnitude());
	}
}
