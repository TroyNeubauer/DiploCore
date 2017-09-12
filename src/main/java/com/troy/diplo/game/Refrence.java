package com.troy.diplo.game;

import com.troyberry.util.serialization.TroySerializable;

public interface Refrence extends TroySerializable {
	
	public int getID();

	public void setID(int id);
	
	public void readIDs();
}
