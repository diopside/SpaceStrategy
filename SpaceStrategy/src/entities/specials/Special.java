package entities.specials;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import entities.Planet;
import graphics.Listable;

abstract public class Special implements Listable {

	//create thumbnails for special to be viewed in info boxes
	private String desc;
	private Image thumb;
	
	@Override
	public String getDesc(){
		return desc;
	}
	@Override
	public  Image getThumbnail() throws SlickException{
		return thumb;
	}
	
	
	public abstract void applySpecial(Planet p);
}
