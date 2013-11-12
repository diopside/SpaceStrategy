package entities.buildings;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import entities.Planet;
import graphics.InfoBox;

public class House extends Building{

		
	private byte populationCapacity;
	
	
	//*************************************** Constructors and initialization methods ********************************
	public House(short ID, Planet p){
		this.ID = ID;
		this.laborUsage = 0;
		addBuilding(p);
		populationCapacity = 5;
	}
	
	public House(){
		
	}
	
	
	
	
	
	
	
	
	//******************************* Interface and Inherited Methods ***********************************
	@Override
	public boolean canBuild(Planet p) {
		return false;

	}

	@Override
	public void addBuilding(Planet p) {
		// TO DO: determine the population added by determining the technology of the civ
		p.setMaximumPopulation(p.getMaximumPopulation() + populationCapacity);
		
	}

	@Override
	public void removeBuilding(Planet p) {
		
		p.setMaximumPopulation(p.getMaximumPopulation() - populationCapacity);
		p.getBuildings().remove(this);
	}

	@Override
	public void resolveBuildingTurn(Planet p) {
		// houses don't do much
	}


	@Override
	public Image getThumbnail() throws SlickException {
		return new Image("res/thumbnails/housethumb.png");
	}

	@Override
	public String getDesc() {
		return "Adds to the maximum population of the planet";
	}
	
	
	

	
}
