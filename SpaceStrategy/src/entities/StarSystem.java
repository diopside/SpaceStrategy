package entities;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;

import graphics.Clickable;
import graphics.Renderable;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import states.SpaceStrategy;

public class StarSystem implements Renderable, Clickable {

	//*****************************Static and final members*********************************************
	public static final int SIZE = 50; // this will mostly be used for boundary testing on the star system
	private final int ID;
	
	
	//************************************Ordinary Members*******************************************
	private int x, y, imgIndex;
	private String name;
	private Planet[] planets;

	private static Image[] STAR_IMAGES; // by storing 6 images instead how many instances of star system exist memory should be preserved

	
	
	public StarSystem(int x, int y, int numPlanets, String name, int id){
		ID = id;
		this.name = name;
		this.x = x;
		this.y = y;
		planets = new Planet[numPlanets];
		imgIndex = (int) (Math.random() *6); // get a random image
		
		generatePlanets();
	}
	
	//**************************************** Getters and Setters************************************************************
	public Planet[] getPlanets(){
		return planets;
	}
	public int getID(){
		return ID;
	}
	public String getName(){
		return name;
	}
	
	
	//********************************************* General Methods **************************************************************

	public int getNumberOfFactions(){
		Vector<Integer> factions = new Vector<Integer>();
		for (Planet p: planets)
			if (p.getOwner() != null){
				if (factions.size() == 0)
					factions.add(p.getOwner().getID());
				else if (factions.size() == 1){
					if (factions.get(0) != p.getOwner().getID())
						return 2;
				}
			}
		return factions.size();
	}
	
	private Color getFactionColor(){
		for (Planet p: planets){
			if (p.getOwner() != null){
				return p.getOwner().getCOLOR();
			}
		}
		return Color.white;
	}
	
	public Color getSystemColor(){
		int numFactions = getNumberOfFactions();
		if (numFactions == 0) return Color.white;
	        if (numFactions == 1) return getFactionColor();
		return Color.gray;
	}
	

	public void setPlanetRenderTransparency(int planetIndex){
		for (int i = 0; i < planets.length; i ++){
			if (i != planetIndex)
				planets[i].setDrawTransparent(true);
			else
				planets[i].setDrawTransparent(false);
		}
	}
	
	private int getX(){
		return x;
	}
	private int getY(){
		return y;
	}
	
	// *******************************Static Methods *******************************************************************************
	
	
	
	private void generatePlanets(){
		for (int i = 0; i < planets.length; i ++){
			int size = (int) (Math.random() * 8 + 3);
			planets[i] = new Planet(name, size, i, ID);
		}
	}
	
	public static void initStarImages(){
		STAR_IMAGES = new Image[6];
		for (int i = 0; i < STAR_IMAGES.length; i ++)
			try {
				STAR_IMAGES[i] = new Image("res/stars/star"+i+".png");
			} catch (SlickException exception) {
				exception.printStackTrace();

			}
	} // end static method





	
	//*************************** Interface Methods ***********************************************************************************


	@Override
	public void render(Graphics g, int xOffset, int yOffset) {
		StarSystem.STAR_IMAGES[imgIndex].draw(x - xOffset, y - yOffset);
	
		g.setFont(SpaceStrategy.GALANT);
		g.setColor(getSystemColor());
		g.drawString(name, x - xOffset, y - yOffset + SIZE + 10);
	}



	@Override
	public Shape getShape() {
		return new Rectangle(x, y, SIZE, SIZE);
	}

	@Override
	public boolean contains(int x, int y) {
		return getShape().contains(x, y);
	}
	
	
	
} // end class
