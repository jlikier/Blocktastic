/*
 * Copyright 2010 - James Likier and Julian Hartline
 */
package blocklib.entity;

import blocklib.common.*;

public class Entity {
	public int entityID; //this allows the client to know npc this is
	public int factionID;
	public int uniqueID;  //this is the unique identifier given to each instance of an npc/monster/player
	public int claimedByID; //which player has claim of this entity
	public int targettingID; //the entity this entity is targetting
	public int textureID; //what texture is this entity using
	public Vector3F position;
	public Vector3F orientation;
	public Vector3F dimension;
	public Vector3F velocity;
	public int health;
	public int mana;
	public int level;
	public int state; //bitwise flag
}
