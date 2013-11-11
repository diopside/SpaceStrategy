package entities.buildings;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import entities.Planet;

public class Factory extends Building{

	
	private byte productionValue;
	
	public Factory(short ID, Planet p){
		this.ID = ID;
		addBuilding(p);
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
	}

	@Override
	public void removeBuilding(Planet p) {
		
	}

	@Override
	public void resolveBuildingTurn(Planet p) {
		
	}

}
