package com.troy.diplo.turn;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.troyberry.logging.Timer;
import com.troyberry.util.serialization.*;

public class TurnTime implements TroySerializable {

	private long time;// epoch time, (the number of milliseconds since January 1, 1970 UTC time)

	public TurnTime(long time) {
		this.time = time;
	}

	public long getTime() {
		return time;
	}

	public String getTimeString() {
		return new SimpleDateFormat("EEE, MMMM dd, yyyy hh:mm aa").format(new Date(time));
	}

	public String timeUntil() {
		return Timer.getString(relativeTime() * 1000000);
	}

	public String getFullInfoString() {
		long relativeTime = relativeTime();
		if (relativeTime > 0) {
			return "Turn in " + Timer.getString(relativeTime * 1000000) + " (" + getTimeString() + ")";
		}
		return    "Turn was " + Timer.getString(relativeTime * 1000000) + " ago (" + getTimeString() + ")";
	}

	/**
	 * Returns the number of nanoseconds between now and this turn time. <br>
	 * A positive value indicates that this turn is in the future, while a negative value indicates that this turn already occurred
	 * 
	 * @return The number of nanoseconds between now and this turn time
	 */
	public long relativeTime() {
		return time - System.currentTimeMillis();
	}

	public boolean hasHappend() {
		return time <= System.currentTimeMillis();
	}

	@Override
	public String toString() {
		return "TurnTime [time=" + time + ", getFullInfoString()=" + getFullInfoString() + "]";
	}

	@Override
	public void read(TroyBuffer buffer) {
		this.time = buffer.readLong();
	}

	@Override
	public void write(TroyBuffer buffer) {
		buffer.writeLong(time);
	}
	
	

}
