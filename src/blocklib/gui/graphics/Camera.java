package blocklib.gui.graphics;

import org.lwjgl.opengl.DisplayMode;

import blocklib.common.Vector3F;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

public class Camera {
	Vector3F position;
	Vector3F heading;
	Vector3F up;
	
	//Changing these could be bad if not done together
	private DisplayMode mode;
	private float aspect;
	
	public Camera(DisplayMode mode, Vector3F position, Vector3F heading, Vector3F up) {
		this.mode = mode;
		this.position = position;
		this.heading = heading;
		this.up = up;
		
		aspect = mode.getWidth()/(float)mode.getHeight();
	}
	
	public void transform() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(45F, aspect, 1F, 1000F);
		glTranslatef(position.x, position.y, position.z);

		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
}
