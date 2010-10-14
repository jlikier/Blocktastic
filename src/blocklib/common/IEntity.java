/*
 * Copyright 2010 - James Likier
 */
package blocklib.common;

public interface IEntity {
	
	int getTextureID();
	void setTextureID(int tid);
	
	Position getPosition();
	void setPosition(Position p);
	
	Dimension getDimension();
	void setDimension(Dimension d);
	
	Movement getMovement();
	void setMovement(Movement m);
	
	void Update();

}
