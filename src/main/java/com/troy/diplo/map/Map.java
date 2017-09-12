package com.troy.diplo.map;

import java.io.File;
import java.util.List;

import com.troy.diplo.game.*;
import com.troy.diplo.turn.TurnStrategy;

public class Map {
	private String name;
	private File origin;
	private long lastModified;
	private Account owner;
	private List<Account> sharedWith;
	private ProvinceList provinces;

	public Map(String name, File origin, long lastModified, Account owner, List<Account> sharedWith, ProvinceList provinces) {
		this.name = name;
		this.origin = origin;
		this.lastModified = lastModified;
		this.owner = owner;
		this.sharedWith = sharedWith;
		this.provinces = provinces;
	}
	
	public Game createGame(String gameName, List<Team> teams, TurnStrategy strategy) {
		try {
			Game game = new Game(gameName, teams, (ProvinceList) provinces.clone(), strategy);
			Game.addAndSetID(game);
			return game;
		} catch (CloneNotSupportedException e) {
			throw new UnsupportedOperationException(e);
		}
		
	}

	public File getOrigin() {
		return origin;
	}

	public void setOrigin(File origin) {
		this.origin = origin;
	}

	public long getLastModified() {
		return lastModified;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	public String getName() {
		return name;
	}

	public Account getOwner() {
		return owner;
	}

	public List<Account> getSharedWith() {
		return sharedWith;
	}

	public ProvinceList getProvinces() {
		return provinces;
	}

}
