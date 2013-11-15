package entities.ships;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import entities.Planet;
import entities.shipitems.*;

public class ColonyShip extends Ship{

	
	public ColonyShip(int id, Thruster thruster, Weapon[] weapons, Armor armor, Shield shield){
		this.thruster = thruster;
		this.weapons = weapons;
		this.armor = armor;
		this.shield = shield;
		this.productionCost= startProductionCost = 10;
		this.designName = "DefaultColony";
		ID = (short) id;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	// ******************************************* Interface Methods *******************************************
	@Override
	public boolean canBuild(Planet p) {
		return true;
	}


	@Override
	public Image getThumbnail() throws SlickException {
		return new Image("res/thumbnails/fleetthumb.png");
	}

	@Override
	public String getDesc() {
		return (designName +"  "+"Establishes a new planet for the faction");
	}

	

	
	@Override
	public void remove(Planet p) {
		
	}

}
