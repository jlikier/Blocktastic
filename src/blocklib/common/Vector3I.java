/*
 * Copyright 2010 - James Likier and Julian Hartline
 */
package blocklib.common;

public class Vector3I {
	public int x;
	public int y;
	public int z;
	
	public Vector3I(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3I clone()
	{
		return new Vector3I(x,y,z);
	}

	public int getMagnitude()
	{
		return (int)Math.cbrt(x*x + y*y + z*z);
	}

	public Vector3I add(Vector3I v)
	{
		return new Vector3I(x+v.x, y+v.y, z+v.z);
	}
	public Vector3I add(int x, int y, int z)
	{
		return new Vector3I(this.x + x, this.y + y, this.z + z);
	}

	public Vector3I scale(float factor)
	{
		return new Vector3I((int)(x*factor), (int)(y*factor), (int)(z*factor));
	}

	// Returns a unit vector in the same direction
	public Vector3I getUnitVector()
	{
		return this.scale(1.0F / getMagnitude());
	}
}
