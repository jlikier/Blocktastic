package testing;

import blocklib.common.*;
import blocklib.map.*;
import blocklib.map.block.*;

public class TestRunner {

	public static void main(String[] args) {
		TestRunner r = new TestRunner();
		
		r.ChunkOptimizationTest();
	}
	
	public void ChunkOptimizationTest()
	{
		long sTime, eTime;
		
		Chunk c = new Chunk(new Vector3I(8,8,8));
		System.out.println("New Chunk: " + c.chunkSize);
		
		ChunkOptimization co = new ChunkOptimization(c.chunkSize);
		System.out.println("Visible Blocks: " + co.blockCount);
		
		sTime = System.currentTimeMillis();
		co.CalculateChunk(c);
		eTime = System.currentTimeMillis();
		System.out.println("Visible Blocks: " + co.blockCount);
		System.out.println("Time to execute: " + (eTime - sTime) + "ms");
		
		c.setBlock(new Vector3I(5,5,5), new Block());
		
		sTime = System.currentTimeMillis();
		co.CalculateChunk(c);
		eTime = System.currentTimeMillis();
		System.out.println("Visible Blocks: " + co.blockCount);
		System.out.println("Time to execute: " + (eTime - sTime) + "ms");
		
		c.setBlock(new Vector3I(5,6,5), new Block());
		
		sTime = System.currentTimeMillis();
		co.CalculateChunk(c);
		eTime = System.currentTimeMillis();
		System.out.println("Visible Blocks: " + co.blockCount);
		System.out.println("Time to execute: " + (eTime - sTime) + "ms");
		
		c.setBlock(new Vector3I(5,4,5), new Block());
		
		sTime = System.currentTimeMillis();
		co.CalculateChunk(c);
		eTime = System.currentTimeMillis();
		System.out.println("Visible Blocks: " + co.blockCount);
		System.out.println("Time to execute: " + (eTime - sTime) + "ms");
		
		c.setBlock(new Vector3I(4,5,5), new Block());
		
		sTime = System.currentTimeMillis();
		co.CalculateChunk(c);
		eTime = System.currentTimeMillis();
		System.out.println("Visible Blocks: " + co.blockCount);
		System.out.println("Time to execute: " + (eTime - sTime) + "ms");
		
		c.setBlock(new Vector3I(6,5,5), new Block());
		
		sTime = System.currentTimeMillis();
		co.CalculateChunk(c);
		eTime = System.currentTimeMillis();
		System.out.println("Visible Blocks: " + co.blockCount);
		System.out.println("Time to execute: " + (eTime - sTime) + "ms");
		
		c.setBlock(new Vector3I(5,5,4), new Block());
		
		sTime = System.currentTimeMillis();
		co.CalculateChunk(c);
		eTime = System.currentTimeMillis();
		System.out.println("Visible Blocks: " + co.blockCount);
		System.out.println("Time to execute: " + (eTime - sTime) + "ms");
		
		c.setBlock(new Vector3I(5,5,6), new Block());
		
		sTime = System.currentTimeMillis();
		co.CalculateChunk(c);
		eTime = System.currentTimeMillis();
		System.out.println("Visible Blocks: " + co.blockCount);
		System.out.println("Time to execute: " + (eTime - sTime) + "ms");
	}
}
