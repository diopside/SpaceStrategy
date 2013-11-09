package graphics;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

abstract class Menu implements Clickable, Renderable {
	
	protected Image window;
	protected int x, y;
	
	public Menu(int x, int y, String loc){
		this.x = x;
		this.y = y;
		initWindow(loc);
	}
	


	private void initWindow(String loc){
		try {
			window = new Image(loc);
		} catch (SlickException exception) {
			exception.printStackTrace();
		}
	}


}
