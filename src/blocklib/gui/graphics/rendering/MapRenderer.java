package blocklib.gui.graphics.rendering;

import blocklib.common.*;
import blocklib.map.*;
import java.util.*;

public class MapRenderer {
	public HashMap<String, OptimizedChunk> chunks;
	
	public MapRenderer(Vector3I chunkSize)
	{
		this.chunks = new HashMap<String, OptimizedChunk>();
	}
	
	public void addChunk(Chunk chunk, Vector3I position)
	{
		chunks.put(position.toString(), new OptimizedChunk(chunk, position));
	}
	public void removeChunk(Vector3I position)
	{
		chunks.remove(position.toString());
	}
	
	public void render()
	{
		for(OptimizedChunk c : chunks.values())
		{
			c.render();
		}
	}
}
