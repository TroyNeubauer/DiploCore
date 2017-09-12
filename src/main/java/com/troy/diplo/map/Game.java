package com.troy.diplo.map;

import java.util.List;

import com.troy.diplo.game.*;
import com.troy.diplo.turn.TurnStrategy;
import com.troyberry.util.serialization.TroyBuffer;

public class Game implements Refrence {
	
	private static TypeReader<Game> reader;

	public static void setReader(TypeReader<Game> reader) {
		Game.reader = reader;
	}

	public static Game lookup(int id) {
		return reader.lookup(id);
	}
	
	public static void addAndSetID(Game obj) {
		reader.addAndSetID(obj);
	}

	private String gameName;
	private List<Team> teams;
	private int[] teamIDs;
	private ProvinceList currentMap;
	private java.util.Map<ProvinceList, java.util.Map<Team, TurnData>> turns;
	private int turnNumber;
	private TurnStrategy strategy;
	private int gameId;

	public Game(String gameName, List<Team> teams, ProvinceList currentMap, TurnStrategy strategy) {
		this.gameName = gameName;
		this.teams = teams;
		this.currentMap = currentMap;
		this.turns = new java.util.LinkedHashMap<ProvinceList, java.util.Map<Team, TurnData>>();
		this.strategy = strategy;
	}

	public Game() {
	}

	public String getGameName() {
		return gameName;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public ProvinceList getCurrentMap() {
		return currentMap;
	}

	public int getTurnNumber() {
		return turnNumber;
	}

	public TurnStrategy getStrategy() {
		return strategy;
	}

	@Override
	public int getID() {
		return gameId;
	}
	
	@Override
	public void setID(int id) {
	}
	
	public java.util.Map<ProvinceList, java.util.Map<Team, TurnData>> getTurns() {
		return turns;
	}

	@Override
	public void read(TroyBuffer buffer) {
		this.gameName = buffer.readString();
		int length = buffer.readInt();
		this.teamIDs = new int[length];
		for(int i = 0; i < length; i++) {
			teamIDs[i] = buffer.readInt();
		}
		this.currentMap = TroyBuffer.createInstance(ProvinceList.class);
		currentMap.read(buffer);
		this.turnNumber = buffer.readInt();
		this.strategy = TroyBuffer.createInstance(TurnStrategy.class);
		strategy.read(buffer);
		this.gameId = buffer.readInt();
	}

	@Override
	public void readIDs() {
		for(int i = 0; i < teamIDs.length; i++) {
			Team team = Team.lookup(teamIDs[i]);
			teams.add(team);
		}
		teamIDs = null;//help GC
	}

	@Override
	public void write(TroyBuffer buffer) {
		buffer.writeString(gameName);
		buffer.writeInt(teams.size());
		for(int i = 0; i < teams.size(); i++) {
			buffer.writeInt(teams.get(i).getID());
		}
		currentMap.write(buffer);
		//map later
		buffer.writeInt(turnNumber);
		strategy.write(buffer);
		buffer.writeInt(gameId);
		
	}
}
