package com.troy.diplo.map;

public enum SurfaceType {
	GROUND, WATER;

	public boolean canMove(Unit unit) {
		switch (this) {
		case GROUND:
			if (unit.getType() == UnitType.ARMY)
				return true;
			if (unit.getType() == UnitType.FLEET) {
				/*
				for (Province p : unit.getProvince().getAdjacent()) {
					if (p.getSurfaceType() == SurfaceType.WATER) {
						return true;
					}
				}
				*/
				return false;
			}
			return true;
		case WATER:
			if (unit.getType() == UnitType.ARMY)
				return false;
			if (unit.getType() == UnitType.FLEET)
				return true;
		default:
			throw new InternalError();
		}
	}
}
