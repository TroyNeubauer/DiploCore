package com.troy.diplo.map;

import com.troy.diplo.game.Team;

public class TurnData {
	private String order;
	private Team owner;

	public TurnData(String order, Team owner) {
		this.order = order;
		this.owner = owner;
	}

	public String getOrder() {
		return order;
	}

	public Team getOwner() {
		return owner;
	}

}
