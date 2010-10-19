package blocklib.gui.graphics.rendering;

import blocklib.common.*;

public class QuadFace {
	public Vector3F v1;
	public Vector3F v2;
	public Vector3F v3;
	public Vector3F v4;
	
	public QuadFace(float[] vertices)
	{
		v1 = new Vector3F(vertices[0], vertices[1], vertices[2]);
		v2 = new Vector3F(vertices[3], vertices[4], vertices[5]);
		v3 = new Vector3F(vertices[6], vertices[7], vertices[8]);
		v4 = new Vector3F(vertices[9], vertices[10], vertices[11]);
	}
	public QuadFace()
	{
	}
	
	public QuadFace offset(Vector3I offset)
	{
		QuadFace f = new QuadFace();
		f.v1 = v1.add(offset);
		f.v2 = v2.add(offset);
		f.v3 = v3.add(offset);
		f.v4 = v4.add(offset);
		
		//System.out.println(f.v1);
		
		return f;
	}
}
