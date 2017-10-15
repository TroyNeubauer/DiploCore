package com.troy.diplo.province;

import com.troy.diplo.map.*;

public abstract class AbstractEditableProvince<T extends AbstractProvince<T>> extends AbstractProvince<T> {

	public AbstractEditableProvince(String name, boolean isSupplyCenter, SurfaceType surfaceType, AbstractMap<T> map) {
		super(name, isSupplyCenter, surfaceType, map);
	}

}
