package blocklib.gui.graphics;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import blocklib.gui.input.KeyboardHandler;
import blocklib.gui.graphics.rendering.*;
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
				new Vector3F(0, -64F, -512F),//position
				new Vector3F(0, 0, 1),//heading
				new Vector3F(0, 1, 0)//up
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
				//angle = angle + 0.25f;
				angle++;
				glRotatef(angle, 0f, 1f, 0f);
				//glRotatef(45f, 0f, 1f, 0f);
				//renderImpl(); 
				//test1.renderChunkOptimization();
				//test2.DrawTexturedCube();
				//renderVBO();
				mapRenderer.render();
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
		
		float cam_speed = 10.0F;
		if (kb.isDown(Keyboard.KEY_W)) cam.position.z += cam_speed;
		if (kb.isDown(Keyboard.KEY_S)) cam.position.z -= cam_speed;
		
		if (kb.isDown(Keyboard.KEY_A)) cam.heading.x += .01;
		if (kb.isDown(Keyboard.KEY_D)) cam.heading.x -= .01;

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

		glEnable(GL_LIGHT0); 
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
		for(int x = 0; x < 512; x=x+8)
		{
			for(int y = 0; y < 128; y=y+8)
			{
				mapRenderer.addChunk(Chunk.genRandomChunk(chunkSize), new Vector3I(x,y,0));
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
	
}
