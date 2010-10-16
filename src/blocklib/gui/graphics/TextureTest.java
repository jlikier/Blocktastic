package blocklib.gui.graphics;

import java.nio.FloatBuffer;
import java.io.*;

import org.lwjgl.BufferUtils;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import static org.lwjgl.opengl.GL11.*;

public class TextureTest {
	public Texture t;
	
	public TextureTest()
	{
		try
		{
			FileInputStream s = new FileInputStream("textures/texture1.png");
			t = TextureLoader.getTexture("PNG", s);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void DrawTexturedCube()
	{
		t.bind();
		glBegin(GL_QUADS);{
			// Front Face
			glNormal3f( 0.0f, 0.0f, 0.5f);					
			glTexCoord2f(0.0f, 0.125f); glVertex3f(-1.0f, -1.0f,  1.0f);
			glTexCoord2f(0.125f, 0.125f); glVertex3f( 1.0f, -1.0f,  1.0f);
			glTexCoord2f(0.125f, 0.0f); glVertex3f( 1.0f,  1.0f,  1.0f);
			glTexCoord2f(0.0f, 0.0f); glVertex3f(-1.0f,  1.0f,  1.0f);
			// Back Face
			glNormal3f( 0.0f, 0.0f,-0.5f);					
			glTexCoord2f(0.25f, 0.125f); glVertex3f(-1.0f, -1.0f, -1.0f);
			glTexCoord2f(0.25f, 0.0f); glVertex3f(-1.0f,  1.0f, -1.0f);
			glTexCoord2f(0.125f, 0.0f); glVertex3f( 1.0f,  1.0f, -1.0f);
			glTexCoord2f(0.125f, 0.125f); glVertex3f( 1.0f, -1.0f, -1.0f);
			/*
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
			*/
		} glEnd();
	}
}
