package graphics;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

public interface Renderable {
	
	
	public void render(Graphics g, int xOffset, int yOffset);
	
	abstract Shape getShape();
}
