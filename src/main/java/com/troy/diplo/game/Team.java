package com.troy.diplo.game;

import java.util.*;

import com.troy.diplo.map.Game;
import com.troyberry.color.TroyColor;
import com.troyberry.util.serialization.*;

public class Team implements Refrence {
	private static TypeReader<Team> reader;

	public static void setReader(TypeReader<Team> reader) {
		Team.reader = reader;
	}

	public static Team lookup(int id) {
		return reader.lookup(id);
	}
	
	public static void addAndSetID(Team obj) {
		reader.addAndSetID(obj);
	}

	private TroyColor color;
	private List<Account> owners;
	private List<Game> games;
	private int id;
	private transient int[] accountIDs, gameIDs;

	public Team(TroyColor color, int id) {
		this.color = color;
		this.id = id;
	}

	public TroyColor getColor() {
		return color;
	}

	public void setColor(TroyColor color) {
		this.color = color;
	}

	public List<Account> getOwners() {
		return owners;
	}

	public List<Game> getGames() {
		return games;
	}

	public boolean isSoloTeam() {
		return owners.size() == 1;
	}

	public int getID() {
		return id;
	}

	@Override
	public void setID(int id) {
		this.id = id;
	}

	@Override
	public void read(TroyBuffer buffer) {
		this.color = TroyBuffer.createInstance(TroyColor.class);
		color.read(buffer);
		int length = buffer.readInt();
		this.accountIDs = new int[length];
		for (int i = 0; i < length; i++) {
			accountIDs[i] = buffer.readInt();
		}
		length = buffer.readInt();
		this.gameIDs = new int[length];
		for (int i = 0; i < length; i++) {
			gameIDs[i] = buffer.readInt();
		}
	}

	@Override
	public void write(TroyBuffer buffer) {
		color.write(buffer);
		buffer.writeInt(owners.size());
		for (int i = 0; i < owners.size(); i++) {
			buffer.writeInt(owners.get(i).getID());
		}
	}

	@Override
	public void readIDs() {
		this.owners = new ArrayList<Account>(accountIDs.length);
		for (int i = 0; i < accountIDs.length; i++) {
			Account account = Account.lookup(accountIDs[i]);
			owners.add(account);
		}
		this.games = new ArrayList<Game>(gameIDs.length);
		for (int i = 0; i < gameIDs.length; i++) {
			Game account = Game.lookup(gameIDs[i]);
			games.add(account);
		}
	}

}
