package blocklib.gui.graphics;

import java.nio.DoubleBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import blocklib.gui.graphics.rendering.*;
import blocklib.gui.input.KeyboardHandler;
import blocklib.map.Chunk;
import blocklib.common.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

import testing.*;

public class Window {
	public KeyboardHandler kb;
	private DisplayMode	mode;
	boolean fullscreen;
	
	float angle;
	
	Camera cam;
	
	// Begin Testing
	VertexArrayTest test1;
	TextureTest test2;
	VBOTest test3;
	MapRenderer mapRenderer;
	// End Testing
	int mousex;
	int mousey;

	public Window() {
		kb = new KeyboardHandler();
		windowed();
		try {
			Display.create();
			glInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		cam = new Camera(mode,
				new Vector3F(0, 0, -5),//position
				new Quaternion(0, 0, 0, 1)//orientation
				);
	}

	public boolean shouldExit() {
		return Display.isCloseRequested();
	}

	public void render() {
		if (Display.isVisible()) {
			processKeyboard();
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			
			cam.transform();
			
			glPushMatrix(); {
				
				
				angle = angle + 0.15f;
				Vector3F vec = Vector3F.I;
				Vector3F vec2 = new Vector3F();
				
				//vec2 = vec.rotate(new Vector3F(1,0,1), angle);
				//vec = vec.rotate(Vector3F.K, angle);
				
				glScalef(.5F, .5F, .5F);
				glRotatef(30, 1, 1, 0);
				renderCube();
				
//				glBegin(GL_LINE_LOOP); {
//					glVertex3f(0,0,0);
//					glVertex3f(1,0,0);
//					glVertex3f(1,1,0);
//					glVertex3f(1,0,0);
//					glVertex3f(0,0,0);
//					
//					glVertex3f(0,0,1);
//					glVertex3f(0,1,1);
//					glVertex3f(0,1,0);
//					glVertex3f(0,0,0);
//					
//				} glEnd();
				//angle++;
				//glRotatef(angle, 0f, 1f, 0f);
				//glRotatef(45f, 0f, 1f, 0f);
				//renderImpl(); 
				//test1.renderChunkOptimization();
				//test2.DrawTexturedCube();
				//renderVBO();
//				mapRenderer.render();
			} glPopMatrix();
		}
		Display.update();
	}
	
	public void renderVBO()
	{
		int total = 0;
		for(int x = 0; x < 24; x=x+3)
		{
			glPushMatrix(); {
				glTranslatef(x*8,0,0);
				for(int y = 0; y < 24; y++)
				{
					glPushMatrix(); {
						glTranslatef(0,y*8,0);
						//test3.BufferData()
						//test3.BufferData(test1.co.vertices, test1.co.blockCount * 3 * 4 * 6);
						test3.BufferData(test1.batch, test1.batchsize * 3 * 4 * 6);
						test3.render();
					}glPopMatrix();
					total += test1.batchsize;
				}
			}glPopMatrix();
		}
		System.out.println(total);
	}

	private void processKeyboard() {
		kb.handle();
		
		if (kb.isDown(Keyboard.KEY_R)) {
			Mouse.setGrabbed(true);
		}
		
		if (kb.isDown(Keyboard.KEY_F)) {
			Mouse.setGrabbed(false);
		}
		
		float cam_speed = .5F;
		float cam_turn = .005F;
		if (kb.isDown(Keyboard.KEY_W)) cam.position.z += cam_speed;
		if (kb.isDown(Keyboard.KEY_S)) cam.position.z -= cam_speed;
		
		if (kb.isDown(Keyboard.KEY_A)) {
			cam.orientation = Quaternion.axisAngle(0, 1, 0, cam_turn).multiply(cam.orientation);
		}
		
		if (kb.isDown(Keyboard.KEY_D)) { 
			cam.orientation = Quaternion.axisAngle(0, 1, 0, -cam_turn).multiply(cam.orientation);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_M)) {
			if (fullscreen) 
				windowed();
			else
				fullscreen();
		}

		while ( Mouse.next() ) {
			if (Mouse.isGrabbed()) {
				//TODO move this somewhere
				mousex = Mouse.getEventX();
				mousey = Mouse.getEventY();
				
				cam.orientation = new Quaternion();
				
				double turnx = cam_turn*(mousex - mode.getWidth()/2);
				double turny = cam_turn*(mousey - mode.getHeight()/2);
				
				cam.orientation = Quaternion.axisAngle(0, 1, 0, turnx).multiply(cam.orientation);
				cam.orientation = Quaternion.axisAngle(1, 0, 0, turny).multiply(cam.orientation);
			}
		}
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
		//glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_TEXTURE_2D);
		//glEnable(GL_POLYGON_SMOOTH);

		//glEnable(GL_LIGHT0); 
		//FloatBuffer position = BufferUtils.createFloatBuffer(4).put(new float[] {0F,0F,5F,0F}); 
		//glLight(GL_LIGHT0, GL_POSITION, (FloatBuffer)position.flip()); 

		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		//glClearColor(1f, 1f, 1f, 0f);
		//Display.setVSyncEnabled(true);
		
		// Begin Testing
		//test1 = new VertexArrayTest();
		//test2 = new TextureTest();
		//test3 = new VBOTest();
		//test3.GenerateBuffer();
		Vector3I chunkSize = new Vector3I(8,8,8);
		mapRenderer = new MapRenderer(chunkSize);
		//mapRenderer.addChunk(Chunk.genRandomChunk(chunkSize), new Vector3I(0,0,0));
		//mapRenderer.addChunk(Chunk.genRandomChunk(chunkSize), new Vector3I(8,0,0));
		//mapRenderer.addChunk(Chunk.genRandomChunk(chunkSize), new Vector3I(16,0,0));
		for(int x = 0; x < 512; x=x+8)
		{
			for(int z = 0; z < 128; z=z+8)
			{
				mapRenderer.addChunk(Chunk.genRandomChunk(chunkSize), new Vector3I(x,0,z));
			}
		}
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

	public void renderCube() {
		// OK, let's start drawing our planer quads.
		glBegin(GL_QUADS); {

		// Bottom Face.  Red, 75% opaque, magnified texture

		glNormal3f( 0.0f, -1.0f, 0.0f); // Needed for lighting
		glColor4d(0.9,0.2,0.2,1); // Basic polygon color

		glTexCoord2f(0.800f, 0.800f); glVertex3f(-1.0f, -1.0f, -1.0f); 
		glTexCoord2f(0.200f, 0.800f); glVertex3f( 1.0f, -1.0f, -1.0f);
		glTexCoord2f(0.200f, 0.200f); glVertex3f( 1.0f, -1.0f,  1.0f);
		glTexCoord2f(0.800f, 0.200f); glVertex3f(-1.0f, -1.0f,  1.0f);


		// Top face; offset.  White, 50% opaque.

		glNormal3f( 0.0f, 1.0f, 0.0f);
		glColor4d(0.5,0.5,0.5,1);

		glTexCoord2f(0.005f, 1.995f); glVertex3f(-1.0f,  1.3f, -1.0f);
		glTexCoord2f(0.005f, 0.005f); glVertex3f(-1.0f,  1.3f,  1.0f);
		glTexCoord2f(1.995f, 0.005f); glVertex3f( 1.0f,  1.3f,  1.0f);
		glTexCoord2f(1.995f, 1.995f); glVertex3f( 1.0f,  1.3f, -1.0f);


		// Far face.  Green, 50% opaque, non-uniform texture cooridinates.

		glNormal3f( 0.0f, 0.0f,-1.0f);
		glColor4d(0.2,0.9,0.2,.5); 

		glTexCoord2f(0.995f, 0.005f); glVertex3f(-1.0f, -1.0f, -1.3f);
		glTexCoord2f(2.995f, 2.995f); glVertex3f(-1.0f,  1.0f, -1.3f);
		glTexCoord2f(0.005f, 0.995f); glVertex3f( 1.0f,  1.0f, -1.3f);
		glTexCoord2f(0.005f, 0.005f); glVertex3f( 1.0f, -1.0f, -1.3f);


		// Right face.  Blue; 25% opaque

		glNormal3f( 1.0f, 0.0f, 0.0f);
		glColor4d(0.2,0.2,0.9,1);

		glTexCoord2f(0.995f, 0.005f); glVertex3f( 1.0f, -1.0f, -1.0f); 
		glTexCoord2f(0.995f, 0.995f); glVertex3f( 1.0f,  1.0f, -1.0f);
		glTexCoord2f(0.005f, 0.995f); glVertex3f( 1.0f,  1.0f,  1.0f);
		glTexCoord2f(0.005f, 0.005f); glVertex3f( 1.0f, -1.0f,  1.0f);


		// Front face; offset.  Multi-colored, 50% opaque.

		glNormal3f( 0.0f, 0.0f, 1.0f); 

		glColor4f( 0.9f, 0.2f, 0.2f, 0.5f);
		glTexCoord2f( 0.005f, 0.005f); glVertex3f(-1.0f, -1.0f,  1.3f);
		glColor4f( 0.2f, 0.9f, 0.2f, 0.5f);
		glTexCoord2f( 0.995f, 0.005f); glVertex3f( 1.0f, -1.0f,  1.3f);
		glColor4f( 0.2f, 0.2f, 0.9f, 0.5f);
		glTexCoord2f( 0.995f, 0.995f); glVertex3f( 1.0f,  1.0f,  1.3f); 
		glColor4f( 0.1f, 0.1f, 0.1f, 0.5f);
		glTexCoord2f( 0.005f, 0.995f); glVertex3f(-1.0f,  1.0f,  1.3f);


		// Left Face; offset.  Yellow, varying levels of opaque.

		glNormal3f(-1.0f, 0.0f, 0.0f);  

		glColor4d(0.9,0.9,0.2,0.0);
		glTexCoord2f(0.005f, 0.005f); glVertex3f(-1.3f, -1.0f, -1.0f); 
		glColor4d(0.9,0.9,0.2,0.66);
		glTexCoord2f(0.995f, 0.005f); glVertex3f(-1.3f, -1.0f,  1.0f);
		glColor4d(0.9,0.9,0.2,1.0);
		glTexCoord2f(0.995f, 0.995f); glVertex3f(-1.3f,  1.0f,  1.0f);
		glColor4d(0.9,0.9,0.2,0.33);
		glTexCoord2f(0.005f, 0.995f); glVertex3f(-1.3f,  1.0f, -1.0f);

		// All polygons have been drawn.
		} glEnd();
	}

}
