package graphics;

import java.awt.Rectangle;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class FadeButton extends Button {

	private Image image;
	private float alpha;
	
	
	public FadeButton(int x, int y, String loc){
		this.x = x;
		this.y = y;
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
	
	
	@Override
	public boolean contains(int x, int y) {
		Rectangle r = new Rectangle(this.x, this.y, image.getWidth(), image.getHeight());
		return r.contains(x, y);
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
