package entities.ships;

import java.util.ArrayList;

import entities.Constructable;
import entities.Fleet;
import entities.Planet;
import entities.shipitems.*;
import graphics.Listable;

public abstract class Ship implements Constructable {

	protected short ID;
	protected Fleet fleet;
	protected String designName;
	protected int x, y;
	protected Weapon[] weapons;
	protected Shield shield;
	protected Thruster thruster;
	protected Armor armor;
	protected short productionCost, startProductionCost;
	protected boolean isBeingDeconstructed;
	
	
	protected int getConsumedProductionPoints(Planet p){
		int i = (p.getProductionPoints() >= productionCost) ? productionCost : p.getProductionPoints();
		p.setProductionPoints(p.getProductionPoints() - i);
		return i;
	}
	
	@Override
	public void construct(Planet p) {
		productionCost -= getConsumedProductionPoints(p);
	}
	
	@Override
	public float getCompletionPercentage() {
		return (1f - (float)productionCost/startProductionCost);
	}
	
	@Override
	public short getID() {
		return ID;
	}
	
	
	@Override
	public void setDeconstructionStatus(boolean b) {
		isBeingDeconstructed = b;
	}

	@Override
	public boolean isBeingDeconstructed() {
		return isBeingDeconstructed;
	}

	
	public static ArrayList<Ship> getShipList(){
		ArrayList<Ship> ships = new ArrayList<>();
		ships.add(new ColonyShip(0, null, null, null, null));
		ships.add(new WarShip(0, null, null, null, null));
		return ships;
	}
}
