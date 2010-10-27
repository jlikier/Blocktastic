/*
 * Copyright 2010 - James Likier and Julian Hartline
 */
package blocklib.common;

import org.lwjgl.util.vector.Matrix2f;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class Vector3F {
	public static final Vector3F I = new Vector3F(1,0,0);
	public static final Vector3F J = new Vector3F(0,1,0);
	public static final Vector3F K = new Vector3F(0,0,1);
	
	public float x;
	public float y;
	public float z;
	
	public Vector3F(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3F() {
		this(0,0,0);
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
	
	public Vector3F normalize() {
		return getUnitVector();
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
	
	public boolean equals(Object o) {
		if (!(o instanceof Vector3F)) return false;
		Vector3F v = (Vector3F)o;
		if (v.x == this.x && v.y == this.y && v.z == this.z) return true;
		return false;
	}
	
	public Vector3F rotate(Vector3F axis, float angle) {
		Vector3F u;
		Vector3F v = J.clone();
		Vector3F w = axis.clone();
		if (v.equals(w)) {
			v = I.clone();
		}
		u = buildSystem(w,v);
		
//		System.out.println("ru: "+u.toString());
//		System.out.println("rv: "+v.toString());
//		System.out.println("rw: "+w.toString());
		
		//coordinate transformation matrix
		Matrix4f m = new Matrix4f();
		m.m00=u.x;
		m.m01=u.y;
		m.m02=u.z;
		
		m.m10=v.x;
		m.m11=v.y;
		m.m12=v.z;
		
		m.m20=v.x;
		m.m21=v.y;
		m.m22=v.z;
		
		m.m33=1;
		
		//2d rotation matrix
		Matrix4f rot = new Matrix4f();
		rot.m00=(float)Math.cos(angle);
		rot.m01=-(float)Math.sin(angle);
		rot.m10=(float)Math.sin(angle);
		rot.m11=(float)Math.cos(angle);
		rot.m22=1;
		rot.m33=1;
		
		Vector4f vec = new Vector4f(this.x,this.y,this.z,1);
		
		Matrix4f.transform(m,vec, vec); //shift coordinates
		
		Matrix4f.transform(rot, vec, vec); //rotate
		
		Matrix4f.invert(m, m);
		Matrix4f.transform(m,vec, vec); //shift back
		
		Vector3F result = new Vector3F();
		result.x = vec.x/vec.w;
		result.y = vec.y/vec.w;
		result.z = vec.z/vec.w;
		
		return result;
	}
	
	public String toString()
	{
		return "{"+x+", "+y+", "+z+"}";
	}
	
	
	//returns the x axis, perpendicularizes up and heading
	public static Vector3F buildSystem(Vector3F heading, Vector3F up) {
		
		Vector3F u = up.cross(heading); //x axis
		Vector3F v = heading.cross(u); //y axis
		Vector3F w = u.cross(v);  //z axis
		
		up.overwriteWith(v);
		heading.overwriteWith(w);
		
		return u;
	}
}
