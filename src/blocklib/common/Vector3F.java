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
	
	public void overwriteWith(Vector3F b) {
		this.x = b.x;
		this.y = b.y;
		this.z = b.z;
	}

	public float getMagnitude()
	{
		return (float)Math.cbrt(x*x + y*y + z*z);
	}

	public Vector3F add(Vector3F v)
	{
		return new Vector3F(x+v.x, y+v.y, z+v.z);
	}
	
	public Vector3F add(Vector3I v)
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
	
	public Vector3F cross(Vector3F b) {
		//	|i  		j		k   |
		//	|a.x		a.y		a.z |
		//	|b.x		b.y		b.z |
		
		// a x b  =   i|a.y  a.z |   -  j|a.x  a.z|   +  k|a.x  a.y|
		//             |b.y  b.z |       |b.x  b.z|       |b.x  b.y|
		
		Vector3F a = this;
		float xx = det2x2(a.y, a.z, b.y, b.z);
		float yy = -det2x2(a.x, a.z, b.x, b.z);
		float zz = det2x2(a.x, a.y, b.x, b.y);
		
		return new Vector3F(xx,yy,zz);
	}
	
	private float det2x2(float a, float b, float c, float d) {
		//	|a  b|
		//	|c  d|
		return a * d - (b * c);
	}
	
	public String toString()
	{
		return "{"+x+", "+y+", "+z+"}";
	}
}
