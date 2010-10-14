/*
 * Copyright 2010 - James Likier
 */
package blocklib.common;

public interface IEntity {
	
	int getTextureID();
	void setTextureID(int tid);
	
	Vector3F getPosition();
	void setPosition(Vector3F p);
	
	Vector3F getDimension();
	void setDimension(Vector3F d);
	
	Vector3F getVelocity();
	void setVelocity(Vector3F m);
	
	void Update();

}
