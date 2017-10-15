package com.troy.diplo.province;

import java.util.*;

import com.troy.diplo.game.Refrence;
import com.troy.diplo.map.AbstractMap;
import com.troy.diplo.map.SurfaceType;
import com.troyberry.util.serialization.*;

public abstract class AbstractProvince<T extends AbstractProvince<T>> implements TroySerializable, Refrence {

	private String name;
	private boolean isSupplyCenter;
	private SurfaceType surfaceType;
	private int id;
	private List<AbstractProvince<T>> adjacents = new ArrayList<AbstractProvince<T>>();
	private int[] tempIDs;
	private AbstractMap<T> map;

	public AbstractProvince(String name, boolean isSupplyCenter, SurfaceType surfaceType, AbstractMap<T> map) {
		this.name = name;
		this.isSupplyCenter = isSupplyCenter;
		this.surfaceType = surfaceType;
		this.map = map;
	}

	@Override
	public int getID() {
		return id;
	}

	@Override
	public void setID(int id) {
		this.id = id;
	}

	@Override
	public void readIDs() {
		for(int provinceID : tempIDs) {
			adjacents.add(map.lookup(provinceID));
		}
	}

	@Override
	public void read(TroyBuffer buffer) {
	}

	@Override
	public void write(TroyBuffer buffer) {
	}

}
