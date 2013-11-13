package entities;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import entities.ships.Ship;
import graphics.Listable;

public class Fleet implements Listable{
	
	private final short ID;
	private ArrayList<Ship> ships;
	private Leader leader;
	private StarSystem loc;
	private Faction faction;
	private short wins, losses;
	
	public Fleet(int id){
		ID = (short) id;
		ships = new ArrayList<>();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// **************************** Interface Methods **************************
	@Override
	public Image getThumbnail() throws SlickException {
		return new Image("res/thumbnails/fleetthumb.png");
	}

	@Override
	public String getDesc() {
		return ("Leader: " + leader.getName() +"   " +"Size: " + ships.size());
	}

	@Override
	public short getID() {
		return ID;
	}
	
	
	
	
}
