package com.troy.diplo.map;

import com.troyberry.util.serialization.*;

public class Province implements TroySerializable, Cloneable {

	private String name;
	private ProvinceImpl impl;
	private boolean supplyCenter;
	private SurfaceType surfaceType;
	private Unit unit;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public boolean isSupplyCenter() {
		return supplyCenter;
	}

	public SurfaceType getSurfaceType() {
		return surfaceType;
	}
	
	public Object clone() throws CloneNotSupportedException {
		Province result = (Province) super.clone();
		return result;
	}
	
	public Object cloneDeep() throws CloneNotSupportedException {
		Province result = (Province) super.clone();
		result.impl = (ProvinceImpl) impl.clone();
		return result;
	}

	@Override
	public void read(TroyBuffer buffer) {
	}

	@Override
	public void write(TroyBuffer buffer) {
	}
}
