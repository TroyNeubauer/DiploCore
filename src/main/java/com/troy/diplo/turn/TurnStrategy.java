package com.troy.diplo.turn;

import java.util.*;

import com.troyberry.util.serialization.*;

public class TurnStrategy implements TroySerializable {

	private ArrayList<TurnTime> times = new ArrayList<TurnTime>();

	private TurnTimeProvider provider;

	public TurnStrategy(TurnTimeProvider provider) {
		this.provider = provider;
	}

	public TurnTime next() {
		refillQueue(provider.getTurn() + 1);
		return times.get(provider.getTurn());
	}

	public void refillQueue(int maxTurn) {
		for (int i = provider.getTurn(); i <= maxTurn; i++) {
			times.add(provider.next());
		}
	}
	
	public List<TurnTime> getTimes(int turnStart, int turnEnd) {
		refillQueue(turnEnd);
		ArrayList<TurnTime> result = new ArrayList<TurnTime>(turnEnd - turnStart);
		for(int i = turnStart; i <= turnEnd; i++) {
			result.add(times.get(i));
		}
		return result;
	}

	@Override
	public void read(TroyBuffer buffer) {
		times = new ArrayList<TurnTime>();
		int length = buffer.readInt();
		for(int i = 0; i < length; i++) {
			times.add(new TurnTime(buffer.readLong()));
		}
		this.provider = TurnTimeProvider.readObj(buffer);
	}

	@Override
	public void write(TroyBuffer buffer) {
		buffer.writeInt(times.size());
		for(int i = 0; i < buffer.limit(); i++) {
			buffer.writeLong(times.get(i).getTime());
		}
		TurnTimeProvider.writeObj(provider, buffer);
	}

}
