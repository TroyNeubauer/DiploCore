package com.troy.diplo.map;

import java.util.*;

import com.troyberry.util.serialization.*;

public class ProvinceList implements TroySerializable, Cloneable {
	private List<Province> provinces;
	
	public ProvinceList(List<Province> provinces) {
		this.provinces = provinces;
	}

	public ProvinceList() {
		this.provinces = new ArrayList<Province>();
	}

	public void add(Province province) {
		provinces.add(province);
	}
	
	public boolean remove(Province province) {
		return provinces.remove(province);
	}
	
	public List<Province> getProvinces() {
		return provinces;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		List<Province> newProvinces = new ArrayList<Province>(provinces.size());
		for(int i = 0; i < provinces.size(); i++) {
			newProvinces.add((Province) provinces.get(i).clone());
		}
		return new ProvinceList(newProvinces);
		
	}

	@Override
	public void read(TroyBuffer buffer) {
	}

	@Override
	public void write(TroyBuffer buffer) {
	}

}
