package blocklib.gui.graphics.rendering;

import java.util.*;
import blocklib.common.*;
import blocklib.map.*;

public class OptimizedChunk {
	public List<QuadFace> faces;
	
	public OptimizedChunk()
	{
		faces = new ArrayList<QuadFace>();
	}
	public OptimizedChunk(Chunk c, Vector3I offset)
	{
		this();
		generateFaces(c, offset);
	}
	
	public void generateFaces(Chunk c, Vector3I offset)
	{
		//System.out.println(offset);
		faces.add(BlockFace.frontFace.offset(offset));
		faces.add(BlockFace.backFace.offset(offset));
		faces.add(BlockFace.topFace.offset(offset));
		faces.add(BlockFace.bottomFace.offset(offset));
		faces.add(BlockFace.leftFace.offset(offset));
		faces.add(BlockFace.rightFace.offset(offset));
	}
}
