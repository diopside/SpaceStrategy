package graphics;

import java.awt.Rectangle;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class FadeButton extends Button {

	private Image image;
	private float alpha;
	private short buttonID;
	
	
	public FadeButton(int x, int y, String loc, int buttonID){
		this.x = x;
		this.y = y;
		buttonID = buttonID;
		initImage(loc);
		alpha = 1f;
	}
	
	private void initImage(String loc){
		try {
			image = new Image(loc);
		} catch (SlickException exception) {
			exception.printStackTrace();
		}
	}
	
	
	public short getButtonID(){
		return buttonID;
	}
	
	@Override
	public boolean contains(int x, int y) {
		Rectangle r = new Rectangle(this.x, this.y, image.getWidth(), image.getHeight());
		return r.contains(x, y);
	}
	
	public void setLoc(int xLoc, int yLoc){
		x = xLoc;
		y = yLoc;
	}

	public void setAlpha(float f){
		alpha = f;
	}
	@Override
	public void render(Graphics g, int xOffset, int yOffset) {
		image.setAlpha(alpha);
		image.draw(x, y);
	}

}
