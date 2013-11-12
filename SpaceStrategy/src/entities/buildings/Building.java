package entities.buildings;

import java.util.ArrayList;

import org.newdawn.slick.Image;

import entities.Constructable;
import entities.Planet;
import graphics.Listable;

public abstract class Building implements Constructable {

	protected static float laborUsage;
	protected static int productionCost;
	protected static String name;
	protected short ID;
	protected boolean isActive; // this will indicate whether factories or other buildings will be operating at maximum efficiency
	
	
	
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
	
	
	public static ArrayList<Building> getBuildingList(){
		ArrayList<Building> list = new ArrayList<Building>();
		list.add(new House());
		list.add(new Factory());
		
		return list;
	}
	
}
