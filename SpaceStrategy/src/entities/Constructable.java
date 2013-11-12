package entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import graphics.InfoBox;
import graphics.Listable;

public interface Constructable extends Listable {
	
	

	public boolean canBuild(Planet p);
	
	
	
	
}
