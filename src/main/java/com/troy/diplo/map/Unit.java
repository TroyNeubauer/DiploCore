package com.troy.diplo.map;

import com.troy.diplo.game.Team;
import com.troyberry.util.MiscUtil;
import com.troyberry.util.serialization.*;

public class Unit implements TroySerializable {

	private String name;
	private Team owner;
	private UnitType type;
	private Province province;

	public Unit(String name, Team owner, UnitType type, Province province) {
		this.name = name;
		this.owner = owner;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UnitType getType() {
		return type;
	}

	public Team getOwner() {
		return owner;
	}
	
	public Province getProvince() {
		return province;
	}
	
	public void setProvince(Province province) {
		this.province = province;
	}
	
	public void readAndSet(Province province, TroyBuffer buffer) {
		this.province = province;
		this.read(buffer);
	}
	@Override
	public void read(TroyBuffer buffer) {
		this.name = buffer.readString();
		this.owner = Team.lookup(buffer.readInt());
		this.type = MiscUtil.getEnum(UnitType.class, buffer.readByte());
	}

	@Override
	public void write(TroyBuffer buffer) {
		buffer.writeString(name);
		buffer.writeInt(owner.getID());
		buffer.writeByte((byte) type.ordinal());
	}

}
