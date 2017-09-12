package com.troy.diplo.turn;

import com.troyberry.util.serialization.*;

public interface TurnTimeProvider extends TroySerializable {
	
	public TurnTime next();
	
	public String getPattern();
	
	public int getTurn();

	public static TurnTimeProvider readObj(TroyBuffer buffer) {
		byte id = buffer.readByte();
		TurnTimeProvider result = null;
		switch(id) {
		case 0:
			result = TroyBuffer.createInstance(TurnTimeRepeatableProvider.class);
			break;
		case 1:
			result = TroyBuffer.createInstance(TurnTimeSchoolProvider.class);
			break;
		}
		result.read(buffer);
		return result;
	}

	public static void writeObj(TurnTimeProvider endProvider, TroyBuffer buffer) {
		byte id = (byte) ((endProvider.getClass() == TurnTimeRepeatableProvider.class) ? 0 : 1);
		buffer.writeByte(id);
		endProvider.write(buffer);
	}

}
