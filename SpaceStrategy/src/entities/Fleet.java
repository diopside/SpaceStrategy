package entities;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import entities.ships.Ship;
import graphics.Listable;

public class Fleet implements Listable, Removeable{
	
	private final short ID;
	private ArrayList<Ship> ships;
	private Leader leader;
	private Faction faction;
	private short wins, losses;
	private boolean isBeingDeconstructed;
	
	public Fleet(int id){
		ID = (short) id;
		ships = new ArrayList<>();
	}
	public Fleet(int id, ArrayList<Ship> ships){
		this.ships = ships;
		ID = (short) id;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// **************************** Interface Methods **************************
	@Override
	public Image getThumbnail() throws SlickException {
		return new Image("res/thumbnails/fleetthumb.png");
	}

	@Override
	public String getDesc() {
		//return ("Leader: " + leader.getName() +"   " +"Size: " + ships.size());
		return ("Ships: " + ships.size());
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

	@Override
	public void remove(Planet p) {
		p.getOwner().getReserveShips().addAll(ships);
		p.getFleets().remove(this);
		
	}
	
	
	
	
}
