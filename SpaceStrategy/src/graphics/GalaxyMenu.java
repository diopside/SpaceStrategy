package graphics;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

import entities.Faction;
import entities.Game;
import states.GalaxyState;
import states.SpaceStrategy;

public class GalaxyMenu extends Menu {
	
	private Game world;
	
	private Rectangle empBox, econBox, techBox, voidBox, leaderBox, endTurnBox, menuBox, designBox, factionBox;
	private static final int EMP_X = 269, SUB_MENU_Y = 122, ECON_X = 445, TECH_X = 629 , SIZE_X = 165, SIZE_Y = 118, LEADER_X = 975, 
			FACTION_X = 696, DESIGN_X = 1159, MENU_X = 1329 , END_TURN_X = 1479;
	
	private static final int TURN_X = 85, TURN_Y = 75, NAME_X = 115, NAME_Y = 25, PLANET_X = 125, PLANET_Y = 100, 
			CREDITS_X = 115, CREDITS_Y = 130, FLEET_X = 145, FLEET_Y = 175, STRING_Y_SPACE = 8;
	
	public GalaxyMenu(int x, int y, String loc, Game world) {
		super(x, y, loc);
		empBox = new Rectangle(EMP_X, y + SUB_MENU_Y, SIZE_X, SIZE_Y);
		econBox = new Rectangle(ECON_X, y + SUB_MENU_Y, SIZE_X, SIZE_Y);
		techBox = new Rectangle(TECH_X, y + SUB_MENU_Y, SIZE_X, SIZE_Y);
		leaderBox = new Rectangle(LEADER_X, y + SUB_MENU_Y, SIZE_X, SIZE_Y);
		designBox = new Rectangle(DESIGN_X, y + SUB_MENU_Y, SIZE_X, SIZE_Y);
		factionBox = new Rectangle(FACTION_X, y + SUB_MENU_Y, SIZE_X, SIZE_Y);
		menuBox = new Rectangle(MENU_X, y + SUB_MENU_Y, 143, SIZE_Y);
		endTurnBox = new Rectangle(END_TURN_X, y + SUB_MENU_Y, 121, SIZE_Y);
		voidBox = new Rectangle(x + 266, y, 1334, 111);

		this.world = world;
		window.setAlpha(.65f); // make the window slightly transparent to see what's obscured by it

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
		drawStrings(g);
	}

	@Override
	public Shape getShape() {
		return new Rectangle(x, y, window.getWidth(), window.getHeight());
	}


	public void checkMenus(int mouseX, int mouseY, StateBasedGame game){
		
		if (empBox.contains(mouseX, mouseY))	game.enterState(SpaceStrategy.EMPIRE_VIEW_ID, new CloseTransition(true), new CloseTransition(false));
		else if (econBox.contains(mouseX, mouseY))	game.enterState(SpaceStrategy.ECONOMY_VIEW_ID, new CloseTransition(true), new CloseTransition(false));
		else if (techBox.contains(mouseX, mouseY))	game.enterState(SpaceStrategy.TECHNOLOGY_VIEW_ID, new CloseTransition(true), new CloseTransition(false));
		else if (factionBox.contains(mouseX, mouseY))	game.enterState(SpaceStrategy.FACTIONS_STATE_ID, new CloseTransition(true), new CloseTransition(false));
		else if (designBox.contains(mouseX, mouseY))	game.enterState(SpaceStrategy.DESIGN_STATE_ID, new CloseTransition(true), new CloseTransition(false));
		else if (leaderBox.contains(mouseX, mouseY))	game.enterState(SpaceStrategy.LEADER_STATE_ID, new CloseTransition(true), new CloseTransition(false));
		else if (menuBox.contains(mouseX, mouseY))	game.enterState(SpaceStrategy.IN_GAME_MENU_STATE_ID, new CloseTransition(true), new CloseTransition(false));
		else if (endTurnBox.contains(mouseX, mouseY))	game.enterState(SpaceStrategy.END_TURN_STATE_ID, new CloseTransition(true), new CloseTransition(false));
		
	}
	
	

	private void drawStrings(Graphics g){
		
		g.setFont(SpaceStrategy.NEUROPOL);
		Faction player = world.getFactions().get(0);
		g.drawString(""+world.turn(), TURN_X, TURN_Y + y + STRING_Y_SPACE);
		g.drawString(""+player.getName(), NAME_X, NAME_Y + y + STRING_Y_SPACE);
		g.drawString(""+player.getWealth(), CREDITS_X, CREDITS_Y + y + STRING_Y_SPACE);
		
	}

}












