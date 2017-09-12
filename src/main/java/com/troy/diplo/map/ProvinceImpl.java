package com.troy.diplo.map;

import com.troyberry.util.serialization.*;

public abstract class ProvinceImpl implements TroySerializable, Cloneable {

	public static ProvinceImpl readFromBuffer(TroyBuffer buffer) {
		ProvinceImpl impl = null;
		byte id = buffer.readByte();
		switch (id) {
		case 0:
			impl = TroyBuffer.createInstance(ProvinceImpl2D.class);
			break;
		case 1:
			impl = TroyBuffer.createInstance(ProvinceImpl3D.class);
			break;
		default:
			throw new InternalError();
		}
		impl.read(buffer);
		return impl;
	}
	
	public abstract Object clone();

}
