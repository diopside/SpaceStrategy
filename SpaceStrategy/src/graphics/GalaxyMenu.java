package graphics;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

import states.GalaxyState;
import states.SpaceStrategy;

public class GalaxyMenu extends Menu {
	
	private Rectangle empBox, econBox, techBox, voidBox;
	private static final int EMP_X = 269, SUB_MENU_Y = 122, ECON_X = 445, TECH_X = 629 , SIZE_X = 165, SIZE_Y = 118;
	
	public GalaxyMenu(int x, int y, String loc) {
		super(x, y, loc);
		empBox = new Rectangle(EMP_X, y + SUB_MENU_Y, SIZE_X, SIZE_Y);
		econBox = new Rectangle(ECON_X, y + SUB_MENU_Y, SIZE_X, SIZE_Y);
		techBox = new Rectangle(TECH_X, y + SUB_MENU_Y, SIZE_X, SIZE_Y);
		voidBox = new Rectangle(x + 266, y + 0, 534, 111);

		window.setAlpha(.75f); // make the window slightly transparent to see what's obscured by it

	}

	//************************************* Interface and Inherited Abstract Methods ********************************

	@Override
	public boolean contains(int x, int y) {

		if (getShape().contains(x, y) && !voidBox.contains(x, y))
			return true;
		return false;
	}

	@Override
	public void render(Graphics g, int xOffset, int yOffset) {
		window.draw(x, y);
		
	}

	@Override
	public Shape getShape() {
		return new Rectangle(x, y, window.getWidth(), window.getHeight());
	}


	public void checkMenus(int mouseX, int mouseY, StateBasedGame game){
		if (empBox.contains(mouseX, mouseY)){
			GalaxyState gS = (GalaxyState) game.getState(SpaceStrategy.GALAXY_STATE_ID);
			game.enterState(SpaceStrategy.EMPIRE_VIEW_ID, new CloseTransition(true), new CloseTransition(false));
		}
		
		else if (econBox.contains(mouseX, mouseY)){
			GalaxyState gS = (GalaxyState) game.getState(SpaceStrategy.GALAXY_STATE_ID);
			game.enterState(SpaceStrategy.ECONOMY_VIEW_ID, new CloseTransition(true), new CloseTransition(false));
		}
		
		else if (techBox.contains(mouseX, mouseY)){
			GalaxyState gS = (GalaxyState) game.getState(SpaceStrategy.GALAXY_STATE_ID);
			game.enterState(SpaceStrategy.TECHNOLOGY_VIEW_ID, new CloseTransition(true), new CloseTransition(false));
		}
	}

	private void drawStrings(Graphics g){
		
	}

}
