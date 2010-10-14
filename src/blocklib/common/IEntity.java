/*
 * Copyright 2010 - James Likier and Julian Hartline
 */
package blocklib.common;

public interface IEntity {
	
	int getEntityID();
	
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
