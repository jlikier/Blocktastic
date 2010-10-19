package blocklib.gui.graphics.rendering;

public class BlockFace {
	public static float[] top = {
			-0.5f, 0.5f, -0.5f,
			-0.5f, 0.5f, 0.5f,
			0.5f, 0.5f, 0.5f,
			0.5f, 0.5f, -0.5f,
	};
	public static float[] bottom = {
			0.5f, -0.5f, -0.5f,
			0.5f, -0.5f, 0.5f,
			-0.5f, -0.5f, 0.5f,
			-0.5f, -0.5f, -0.5f,
	};
	public static float[] front = {
			-0.5f, 0.5f, 0.5f,
			-0.5f, -0.5f, 0.5f,
			0.5f, -0.5f, 0.5f,
			0.5f, 0.5f, 0.5f,
	};
	public static float[] back = {
			0.5f, 0.5f, -0.5f,
			0.5f, -0.5f, -0.5f,
			-0.5f, -0.5f, -0.5f,
			-0.5f, 0.5f, -0.5f,
	};
	public static float[] left = {
			-0.5f, 0.5f, -0.5f,
			-0.5f, -0.5f, -0.5f,
			-0.5f, -0.5f, 0.5f,
			-0.5f, 0.5f, 0.5f,
	};
	public static float[] right = {
			0.5f, 0.5f, 0.5f,
			0.5f, -0.5f, 0.5f,
			0.5f, -0.5f, -0.5f,
			0.5f, 0.5f, -0.5f
	};
	
	public static QuadFace topFace = new QuadFace(top);
	public static QuadFace bottomFace = new QuadFace(bottom);
	public static QuadFace frontFace = new QuadFace(front);
	public static QuadFace backFace = new QuadFace(back);
	public static QuadFace leftFace = new QuadFace(left);
	public static QuadFace rightFace = new QuadFace(right);
}
