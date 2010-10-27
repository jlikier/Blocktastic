package blocklib.gui.graphics;

import java.nio.DoubleBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.DisplayMode;

import blocklib.common.Vector3F;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

public class Camera {
	Vector3F position;
	Quaternion orientation;
	
	//Changing these could be bad if not done together
	private DisplayMode mode;
	private float aspect;
	
	public Camera(DisplayMode mode, Vector3F position, Quaternion orientation) {
		this.mode = mode;
		this.position = position;
		this.orientation = orientation;
		
		aspect = mode.getWidth()/(float)mode.getHeight();
	}
	
	public void transform() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(45F, aspect, 1F, 1000F);
		
		
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		
		double[] a = orientation.getMatrix();
		DoubleBuffer db = BufferUtils.createDoubleBuffer(16).put(a); 
		glMultMatrix((DoubleBuffer)db.flip());
		
		glTranslatef(position.x, position.y, position.z);
	}
	
//	public void transform() {
//		glMatrixMode(GL_PROJECTION);
//		glLoadIdentity();
//		gluPerspective(45F, aspect, 1F, 1000F);
//		glTranslatef(position.x, position.y, position.z);
//		
//		
//		glMatrixMode(GL_MODELVIEW);
//		glLoadIdentity();
//		
//		Vector3F u;
//		Vector3F v = up.clone();
//		Vector3F w = heading.clone();
//		
//		u = Vector3F.buildSystem(w,v);
//		
////		System.out.println("up: "+up.toString());
////		System.out.println("hd: "+heading.toString());
////		
////		System.out.println("u: "+u.toString());
////		System.out.println("v: "+v.toString());
////		System.out.println("w: "+w.toString());
//		
//		float[] floats = new float[]{
//					u.x,u.y,u.z,0,
//					v.x,v.y,v.z,0,
//					w.x,w.y,w.z,0,
//					  0,  0,  0,1};
//		FloatBuffer fb = BufferUtils.createFloatBuffer(16).put(floats); 
//		glMultMatrix((FloatBuffer)fb.flip());
//	}
}
