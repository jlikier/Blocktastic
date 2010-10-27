package blocklib.gui.graphics;

public class Quaternion {
	public double x,y,z,w;

	public Quaternion(double x, double y, double z, double w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public Quaternion() {
		this(0,0,0,1);
	}
	
	public static Quaternion axisAngle(double x, double y, double z, double theta) {
		//normalize
		double magnitude = Math.sqrt(x*x + y*y + z*z);
		x /= magnitude;
		y /= magnitude;
		z /= magnitude;
		
		//convert to quaternion
		Quaternion result = new Quaternion();
		double sin = Math.sin(theta/2);
		result.w = Math.cos(theta/2);
		result.x = x * sin;
		result.y = y * sin;
		result.z = z * sin;
		
		result = result.normalize();
		return result;
	}
	
	public Quaternion multiply(Quaternion q) {
		Quaternion result = new Quaternion();
        result.w = (this.w*q.w - this.x*q.x - this.y*q.y - this.z*q.z);
        result.x = (this.w*q.x + this.x*q.w + this.y*q.z - this.z*q.y);
        result.y = (this.w*q.y - this.x*q.z + this.y*q.w + this.z*q.x);
        result.z = (this.w*q.z + this.x*q.y - this.y*q.x + this.z*q.w);
        result = result.normalize();
		return result;
	}
	
	public double[] getMatrix() {
		return new double[] {
				1 - 2*y*y - 2*z*z,			2*x*y - 2*w*z,			2*x*z + 2*w*y,		0,
				2*x*y + 2*w*z,				1 - 2*x*x - 2*z*z,		2*y*z - 2*w*x,		0,
				2*x*z - 2*w*y,				2*y*z + 2*w*x,			1 - 2*x*x - 2*y*y,	0,
				0,							0,						0,					1
		};
	}
	
	public Quaternion normalize() {
		double mag = Math.sqrt(x*x + y*y + z*z + w*w);
		return new Quaternion(x/mag,y/mag,z/mag,w/mag);
	}
	
	public String toString() {
		return "{ "+x+", "+y+", "+z+", "+w+" }";
	}
}
