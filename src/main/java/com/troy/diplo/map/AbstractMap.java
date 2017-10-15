package com.troy.diplo.map;

import java.io.File;

import com.troy.diplo.province.AbstractProvince;

public abstract class AbstractMap<T extends AbstractProvince<T>> extends RefrenceTypeHandler<AbstractProvince<T>> {
	
	private String name;

	public AbstractMap(File file) {
		super(file);
	}

}
