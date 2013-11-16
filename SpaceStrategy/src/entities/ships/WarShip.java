package entities.ships;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import entities.Planet;
import entities.shipitems.Armor;
import entities.shipitems.Shield;
import entities.shipitems.Thruster;
import entities.shipitems.Weapon;

public class WarShip extends Ship {

	public WarShip(int id, Thruster thruster, Weapon[] weapons, Armor armor, Shield shield){
		this.thruster = thruster;
		this.weapons = weapons;
		this.armor = armor;
		this.shield = shield;
		this.productionCost= startProductionCost = 10;
		this.designName = "DefaultWarShip";
		ID = (short) id;
	}
	
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
		return (designName + "  A ship used for Battle");
	}

	@Override
	public void remove(Planet p) {
		
	}

}
