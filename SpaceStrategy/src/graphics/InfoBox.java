package graphics;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import states.SpaceStrategy;
import entities.Constructable;

public class InfoBox extends BasicButton implements Clickable, Renderable {
	
	public static final short HEIGHT = 80;
	private Image thumbnail;
	private String desc;
	private boolean renderTransparent;
	
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

	
	public void setInformation(Listable listable){
		try {
			thumbnail = listable.getThumbnail();
		} catch (SlickException exception) {
			exception.printStackTrace();
		}
		desc = listable.getDesc();
	}
	

	@Override
	public void render(Graphics g, int xOffset, int yOffset) {
		image.draw(x, y);
		thumbnail.draw(x, y);
		if (renderTransparent){
			thumbnail.setAlpha(.5f);
			image.setAlpha(.5f);
		}
		g.setFont(SpaceStrategy.NEUROPOL);
		g.setColor(Color.white);
		g.drawString(desc, x + thumbnail.getWidth() * 2, y + image.getHeight()/2);
	}

	@Override
	public Shape getShape() {
		return null;
	}

	@Override
	public boolean contains(int x, int y) {
		return false;
	}

}
