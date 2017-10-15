package com.troy.diplo.province;

import com.troy.diplo.map.*;

public abstract class AbstractGameProvince<T extends AbstractProvince<T>> extends AbstractProvince<T> {

	public AbstractGameProvince(String name, boolean isSupplyCenter, SurfaceType surfaceType, AbstractMap<T> map) {
		super(name, isSupplyCenter, surfaceType, map);
	}

}
