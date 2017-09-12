package com.troy.diplo.turn;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.troyberry.logging.Timer;
import com.troyberry.util.serialization.TroyBuffer;

public class TurnTimeRepeatableProvider implements TurnTimeProvider {

	private long start;// epoch time of the first turn
	private long add; // milliseconds between turns
	private int count; // the number of turns calculated so far

	public TurnTimeRepeatableProvider(long start, long add) {
		this.start = start;
		this.add = add;
		this.count = 0;
	}

	@Override
	public TurnTime next() {
		return new TurnTime(start + count++ * add);
	}

	@Override
	public String getPattern() {
		return "Starting on " + new SimpleDateFormat("EEE, MMMM dd, yyyy hh:mm aa").format(new Date(start)) + " new turn every "
				+ Timer.getString(add);
	}

	public long getStart() {
		return start;
	}

	public long getAdd() {
		return add;
	}

	@Override
	public int getTurn() {
		return count;
	}

	@Override
	public void read(TroyBuffer buffer) {
		this.start = buffer.readLong();
		this.add = buffer.readLong();
		this.count = buffer.readInt();
	}

	@Override
	public void write(TroyBuffer buffer) {
		buffer.writeLong(start);
		buffer.writeLong(add);
		buffer.writeInt(count);
	}

}
