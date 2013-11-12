package graphics;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import states.SpaceStrategy;
import entities.Constructable;

public class InfoBox extends BasicButton implements Clickable, Renderable {
	
	public static final short HEIGHT = 80;
	
	//************************* Ordinary members *******************************************
	private Image thumbnail;
	private String desc;
	private boolean renderTransparent;
	private Listable currentData;
	
	// ************************************ Constructors and Initialization methods ***************************************
	public InfoBox(int x, int y) {
		super(x, y, "res/buttons/systemview/infoboxwindow.png");
		thumbnail = null;
		desc = "EMPTY";
	}
	private void initImage(String loc){
		try {
			thumbnail = new Image(loc);
		} catch (SlickException exception) {
			exception.printStackTrace();
		}
	}
	
	//***************************************** General Methods *****************************************
	
	public Listable getListable(){
		return currentData;
	}

	
	public void setInformation(Listable listable){
		try {
			thumbnail = listable.getThumbnail();
		} catch (SlickException exception) {
			exception.printStackTrace();
		}
		desc = listable.getDesc();
		currentData = listable;
	}
	
	public void renderTransparent(boolean b){
		renderTransparent = b;
	}
	
	
	
	
	//******************************** Interface Methods *********************************************

	@Override
	public void render(Graphics g, int xOffset, int yOffset) {
		
		image.draw(x, y);
		thumbnail.draw(x, y);
		
		
		Float alpha = (renderTransparent) ? .1f : 1f;
		image.setAlpha(alpha);  thumbnail.setAlpha(alpha);
		
		
		g.setFont(SpaceStrategy.NEUROPOL);
		g.setColor(Color.white);
		g.drawString("ID-" + currentData.getID() + "  " +desc, x + thumbnail.getWidth() * 2, y + image.getHeight()/2);
	}

	@Override
	public Shape getShape() {
		return new Rectangle(x, y, image.getWidth(), image.getHeight());
	}

	@Override
	public boolean contains(int x, int y) {
		return getShape().contains(x, y);
	}

}
