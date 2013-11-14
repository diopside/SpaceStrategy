package entities.buildings;

import java.util.ArrayList;

import org.newdawn.slick.Image;

import entities.Activatable;
import entities.Constructable;
import entities.Planet;
import graphics.Listable;

public abstract class Building implements Constructable, Activatable {

	protected static float laborUsage;
	protected static int productionCost;
	protected static String name;
	protected short ID;
	protected boolean isActive, isBeingDeconstructed; // this will indicate whether factories or other buildings will be operating at maximum efficiency
	
	
	
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
	
	public boolean isActive(){
		return isActive;
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
	
}
