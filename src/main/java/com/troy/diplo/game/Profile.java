package com.troy.diplo.game;

import java.util.*;

import com.troy.diplo.map.Game;
import com.troyberry.util.serialization.*;

public class Profile implements TroySerializable {
	
	private Account account;
	
	private List<Game> currentGames;
	
	private List<Game> oldGames;
	
	private float highestPlacePercentile;

	public Profile(Account account) {
		this.account = account;
		this.currentGames = new ArrayList<Game>();
		this.oldGames = new ArrayList<Game>();
	}

	public Account getAccount() {
		return account;
	}
	
	public List<Game> getCurrentGames() {
		return currentGames;
	}
	
	public List<Game> getOldGames() {
		return oldGames;
	}
	
	public float getHighestPlacePercentile() {
		return highestPlacePercentile;
	}
	
	public void setHighestPlacePercentile(float highestPlacePercentile) {
		this.highestPlacePercentile = highestPlacePercentile;
	}

	public void readAndSet(TroyBuffer buffer, Account account) {
		this.account = account;
		read(buffer);
	}

	@Override
	public void read(TroyBuffer buffer) {
		
		int length = buffer.readInt();
		this.currentGames = new ArrayList<Game>(length);
		for(int i = 0; i < length; i++) {
			currentGames.add(Game.lookup(buffer.readInt()));
		}
		length = buffer.readInt();
		this.oldGames = new ArrayList<Game>(length);
		for(int i = 0; i < length; i++) {
			oldGames.add(Game.lookup(buffer.readInt()));
		}
		this.highestPlacePercentile = buffer.readFloat();
	}
	

	@Override
	public void write(TroyBuffer buffer) {
		buffer.writeInt(currentGames.size());
		for(int i = 0; i < currentGames.size(); i++) {
			buffer.writeLong(currentGames.get(i).getID());
		}
		buffer.writeInt(oldGames.size());
		for(int i = 0; i < oldGames.size(); i++) {
			buffer.writeLong(oldGames.get(i).getID());
		}
		buffer.writeFloat(highestPlacePercentile);
	
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentGames == null) ? 0 : currentGames.hashCode());
		result = prime * result + Float.floatToIntBits(highestPlacePercentile);
		result = prime * result + ((oldGames == null) ? 0 : oldGames.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profile other = (Profile) obj;
		if (currentGames == null) {
			if (other.currentGames != null)
				return false;
		} else if (!currentGames.equals(other.currentGames))
			return false;
		if (Float.floatToIntBits(highestPlacePercentile) != Float.floatToIntBits(other.highestPlacePercentile))
			return false;
		if (oldGames == null) {
			if (other.oldGames != null)
				return false;
		} else if (!oldGames.equals(other.oldGames))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Profile [currentGames=" + currentGames + ", oldGames=" + oldGames + ", highestPlacePercentile=" + highestPlacePercentile + "]";
	}

}
