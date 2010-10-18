package blocklib.gui.graphics;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import blocklib.gui.input.KeyboardHandler;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

public class Window {
	public KeyboardHandler kb;
	private DisplayMode	mode;
	boolean fullscreen;

	float angle;
	
	// Begin Testing
	VertexArrayTest test1;
	TextureTest test2;
	// End Testing

	public Window() {
		kb = new KeyboardHandler();
		windowed();
		try {
			Display.create();
			glInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean shouldExit() {
		return Display.isCloseRequested();
	}

	public void render() {
		if (Display.isVisible()) {
			processKeyboard();
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glPushMatrix(); {
				glRotatef(angle++, 0f, 1f, 0f);
				//renderImpl(); 
				test1.renderChunkOptimization();
				//test2.DrawTexturedCube();
			} glPopMatrix();
		}
		Display.update();
	}


	public void renderImpl() {
		angle += 1;

		glPushMatrix(); {
			glRotatef(angle, 1, 1, 0);

			glBegin(GL_QUADS); {
				// Front Face
				glNormal3f( 0.0f, 0.0f, 0.5f);					
				glTexCoord2f(0.0f, 0.0f); glVertex3f(-1.0f, -1.0f,  1.0f);
				glTexCoord2f(1.0f, 0.0f); glVertex3f( 1.0f, -1.0f,  1.0f);
				glTexCoord2f(1.0f, 1.0f); glVertex3f( 1.0f,  1.0f,  1.0f);
				glTexCoord2f(0.0f, 1.0f); glVertex3f(-1.0f,  1.0f,  1.0f);
				// Back Face
				glNormal3f( 0.0f, 0.0f,-0.5f);					
				glTexCoord2f(1.0f, 0.0f); glVertex3f(-1.0f, -1.0f, -1.0f);
				glTexCoord2f(1.0f, 1.0f); glVertex3f(-1.0f,  1.0f, -1.0f);
				glTexCoord2f(0.0f, 1.0f); glVertex3f( 1.0f,  1.0f, -1.0f);
				glTexCoord2f(0.0f, 0.0f); glVertex3f( 1.0f, -1.0f, -1.0f);
				// Top Face
				glNormal3f( 0.0f, 0.5f, 0.0f);					
				glTexCoord2f(0.0f, 1.0f); glVertex3f(-1.0f,  1.0f, -1.0f);
				glTexCoord2f(0.0f, 0.0f); glVertex3f(-1.0f,  1.0f,  1.0f);
				glTexCoord2f(1.0f, 0.0f); glVertex3f( 1.0f,  1.0f,  1.0f);
				glTexCoord2f(1.0f, 1.0f); glVertex3f( 1.0f,  1.0f, -1.0f);
				// Bottom Face
				glNormal3f( 0.0f,-0.5f, 0.0f);					
				glTexCoord2f(1.0f, 1.0f); glVertex3f(-1.0f, -1.0f, -1.0f);
				glTexCoord2f(0.0f, 1.0f); glVertex3f( 1.0f, -1.0f, -1.0f);
				glTexCoord2f(0.0f, 0.0f); glVertex3f( 1.0f, -1.0f,  1.0f);
				glTexCoord2f(1.0f, 0.0f); glVertex3f(-1.0f, -1.0f,  1.0f);
				// Right Face
				glNormal3f( 0.5f, 0.0f, 0.0f);					
				glTexCoord2f(1.0f, 0.0f); glVertex3f( 1.0f, -1.0f, -1.0f);
				glTexCoord2f(1.0f, 1.0f); glVertex3f( 1.0f,  1.0f, -1.0f);
				glTexCoord2f(0.0f, 1.0f); glVertex3f( 1.0f,  1.0f,  1.0f);
				glTexCoord2f(0.0f, 0.0f); glVertex3f( 1.0f, -1.0f,  1.0f);
				// Left Face
				glNormal3f(-0.5f, 0.0f, 0.0f);					
				glTexCoord2f(0.0f, 0.0f); glVertex3f(-1.0f, -1.0f, -1.0f);
				glTexCoord2f(1.0f, 0.0f); glVertex3f(-1.0f, -1.0f,  1.0f);
				glTexCoord2f(1.0f, 1.0f); glVertex3f(-1.0f,  1.0f,  1.0f);
				glTexCoord2f(0.0f, 1.0f); glVertex3f(-1.0f,  1.0f, -1.0f);
			} glEnd();

		} glPopMatrix();
	}

	private void processKeyboard() {
		kb.handle();

		if (Keyboard.isKeyDown(Keyboard.KEY_M)) {
			if (fullscreen) 
				windowed();
			else
				fullscreen();
		}

		while ( Mouse.next() );
	}


	public void fullscreen() {
		fullscreen = true;
		try {
			mode = findDisplayMode(800, 600, Display.getDisplayMode().getBitsPerPixel());
			Display.setDisplayModeAndFullscreen(mode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void windowed() {
		fullscreen = false;
		try {
			mode = new DisplayMode(640, 480);
			Display.setDisplayModeAndFullscreen(mode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void glInit() {
		//glEnable(GL_LIGHTING); 
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_TEXTURE_2D);
		//glEnable(GL_POLYGON_SMOOTH);

		//glEnable(GL_LIGHT0); 
		//FloatBuffer position = BufferUtils.createFloatBuffer(4).put(new float[] {0F,0F,5F,0F}); 
		//glLight(GL_LIGHT0, GL_POSITION, (FloatBuffer)position.flip()); 

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(45F, mode.getWidth() / (float)mode.getHeight(), 1F, 1000F);
		glTranslatef(0,-64,-127);

		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		//glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glClearColor(1f, 1f, 1f, 0f);
		//Display.setVSyncEnabled(true);
		
		// Begin Testing
		test1 = new VertexArrayTest();
		test2 = new TextureTest();
		// End Testing
	}

	private DisplayMode findDisplayMode(int width, int height, int bpp) throws LWJGLException {
		DisplayMode[] modes = Display.getAvailableDisplayModes();
		for ( DisplayMode mode : modes ) {
			//Log.p.out("Display Mode: "+mode.toString());
			if ( mode.getWidth() == width && mode.getHeight() == height && mode.getBitsPerPixel() >= bpp && mode.getFrequency() <= 60 ) {
				return mode;
			}
		}
		return Display.getDesktopDisplayMode();
	}

	public void cleanup() {
		Display.destroy();
	}
	
}
