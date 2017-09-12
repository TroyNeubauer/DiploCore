package com.troy.diplo.game;

public interface TypeReader<T extends Refrence> {
	
	public void readIDs();

	public T lookup(int id);

	public void addAndSetID(T obj);
	
	public void cleanUp();

}
