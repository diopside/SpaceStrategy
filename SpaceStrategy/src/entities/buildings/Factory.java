package entities.buildings;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import entities.Planet;

public class Factory extends Building{

	
	private byte productionValue;
	
	public Factory(short id, Planet p){
		this.ID = p.getPlanetBuildingIDMax();
		addBuilding(p);
		productionValue = 2;
		productionCost = startProductionCost = 5;
		laborUsage = 1;
	}
	
	
	
	
	
	
	
	// **************************** Interface and Inherited methods **************************
	@Override
	public boolean canBuild(Planet p) {
		return false;
	}

	@Override
	public Image getThumbnail() throws SlickException {
		return new Image("res/thumbnails/factorythumb.png");
	}

	@Override
	public String getDesc() {
		return "Adds to the maximum production of the planet";
	}

	@Override
	public void addBuilding(Planet p) {
		p.setProduction(p.getProduction() + productionValue);
		
	}

	@Override
	public void removeBuilding(Planet p) {
		
	}

	@Override
	public void resolveBuildingTurn(Planet p) {
		
	}
	@Override
	public void remove() {
		
	}
	@Override
	public String viewActivityInformation() {
		return "This building consumes energy and minerals";
	}


	

}








