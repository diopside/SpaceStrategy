package entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import graphics.InfoBox;
import graphics.Listable;

public interface Constructable extends Listable, Removeable {
	
	

	public boolean canBuild(Planet p);
	public void construct(Planet p);
	public float getCompletionPercentage();
	
	
	
	
}
