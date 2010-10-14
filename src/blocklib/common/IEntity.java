/*
 * Copyright 2010 - James Likier
 */
package blocklib.common;

public interface IEntity {
	
	int gTextureID();
	void sTextureID(int tid);
	
	Position gPosition();
	void sPosition(Position p);
	
	Dimension gDimension();
	void sDimension(Dimension d);
	
	Movement gMovement();
	void sMovement(Movement m);
	
	void Update();

}
