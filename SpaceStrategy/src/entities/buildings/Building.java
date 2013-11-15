package entities.buildings;

import java.util.ArrayList;

import org.newdawn.slick.Image;

import entities.Activatable;
import entities.Constructable;
import entities.Planet;
import graphics.Listable;

public abstract class Building implements Constructable, Activatable {

	protected static float laborUsage;
	protected static int productionCost, startProductionCost;
	protected static String name;
	protected short ID;
	protected boolean isActive = true, isBeingDeconstructed = false; // this will indicate whether factories or other buildings will be operating at maximum efficiency
	
	
	
	public abstract void addBuilding(Planet p);
	public abstract void removeBuilding(Planet p);
	public abstract void resolveBuildingTurn(Planet p);
	
	
	
	
	public boolean equals(Building otherBuilding) {
		return (getID() == otherBuilding.getID());
	}
	
	public short getID(){
		return ID;
	}
	
	public String getName(){
		return name;
	}
	

	public float getLaborUsage(){
		return laborUsage;
	}
	public int getProductionCost(){
		return productionCost;
	}
	
	
	public static ArrayList<Building> getBuildingList(Planet p){
		ArrayList<Building> list = new ArrayList<Building>();
		list.add(new House(p.getPlanetBuildingIDMax(), p));
		list.add(new Factory(p.getPlanetBuildingIDMax(), p));
		
		return list;
	}
	@Override
	public void setActivity(boolean b){
		isActive = b;
	}
	@Override
	public void setDeconstructionStatus(boolean b){
		isBeingDeconstructed = b;
	}
	@Override
	public boolean isBeingDeconstructed(){
		return isBeingDeconstructed;
	}
	
	protected int getConsumedProductionPoints(Planet p){
		int i = (p.getProductionPoints() >= productionCost) ? productionCost : p.getProductionPoints();
		p.setProductionPoints(p.getProductionPoints() - i);
		return i;
	}
	
	@Override
	public void construct(Planet p) {
		productionCost -= getConsumedProductionPoints(p);

	}
	
	public float getCompletionPercentage(){
		return (1f - (float)productionCost/startProductionCost);
	}
	
	@Override
	public boolean isActive(){
		return isActive;
	}
	
	
	
}
