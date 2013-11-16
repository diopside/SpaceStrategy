package states;

import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

import entities.Planet;
import entities.StarSystem;
/*
 * 11/11 Status: 2400 SLOC
 */

public class SpaceStrategy extends StateBasedGame{
	
	// STATE IDs
	public static final int MAIN_MENU_ID = 0, GALAXY_STATE_ID = 1, SYSTEM_VIEW_ID = 2, EMPIRE_VIEW_ID = 3, ECONOMY_VIEW_ID = 4, TECHNOLOGY_VIEW_ID = 5
			, END_TURN_STATE_ID = 6, DESIGN_STATE_ID = 7, LEADER_STATE_ID = 8, FACTIONS_STATE_ID = 9, IN_GAME_MENU_STATE_ID = 10, FLEET_CREATION_STATE_ID = 11 ;
	public static TrueTypeFont NEUROPOL, GALANT_ST, GALANT;
	public static Sound click1, beep1, beep2, error;
	
	
	
	public static final int WIDTH = 1600, HEIGHT = WIDTH * 9 / 16;
	
	

	public SpaceStrategy(String name) {
		super(name);
		addState(new MainMenu(MAIN_MENU_ID));
		addState(new GalaxyState(GALAXY_STATE_ID));
		addState(new SystemView(SYSTEM_VIEW_ID));
		addState(new EmpireView(EMPIRE_VIEW_ID));
		addState(new EconomyView(ECONOMY_VIEW_ID));
		addState(new TechnologyView(TECHNOLOGY_VIEW_ID));
		addState(new EndTurnState(END_TURN_STATE_ID));
		addState(new DesignState(DESIGN_STATE_ID));
		addState(new LeaderState(LEADER_STATE_ID));
		addState(new FactionsState(FACTIONS_STATE_ID));
		addState(new InGameMenuState(IN_GAME_MENU_STATE_ID));
		addState(new FleetCreationState(FLEET_CREATION_STATE_ID));
		enterState(MAIN_MENU_ID);
		
	}

	public void initStatesList(GameContainer container) throws SlickException {
		
		StarSystem.initStarImages();
		Planet.initPlanetImages();
		initFont();
		initSound();

		for (int i = 0; i < getStateCount(); i ++)
			getState(i).init(container, this);




	}

	public static void initSound(){
		try {
			click1 = new Sound("res/sounds/click1.wav");
			beep1 = new Sound("res/sounds/beep2.wav");
			beep2 = new Sound("res/sounds/beep1.wav");
			error = new Sound("res/sounds/error.wav");
		} catch (SlickException exception) {
			exception.printStackTrace();
		}
	}
	public static void initFont(){

		// load font from a .ttf file
		try {
			InputStream inputStream = ResourceLoader.getResourceAsStream("res/font/neuropol.ttf");

			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont2 = awtFont2.deriveFont(Font.BOLD, 12f); // set font size
			NEUROPOL = new TrueTypeFont(awtFont2, true);

		} catch (Exception e) {
			e.printStackTrace();
		}   

		try {
			InputStream inputStream = ResourceLoader.getResourceAsStream("res/font/Galantv2.ttf");

			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont2 = awtFont2.deriveFont(Font.BOLD, 18f); // set font size
			GALANT = new TrueTypeFont(awtFont2, true);

		} catch (Exception e) {
			e.printStackTrace();
		}   
		
		try {
			InputStream inputStream = ResourceLoader.getResourceAsStream("res/font/Galantv2s.ttf");

			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont2 = awtFont2.deriveFont(Font.BOLD, 18f); // set font size
			GALANT_ST = new TrueTypeFont(awtFont2, true);

		} catch (Exception e) {
			e.printStackTrace();
		}   
		    
		    
		
	}
	
	public static void main(String[] args){
		
		try {
			AppGameContainer container = new AppGameContainer(new SpaceStrategy("Space Game"), WIDTH, HEIGHT, false);
			container.setTargetFrameRate(100);
			container.setSmoothDeltas(true);
			container.start();
		} catch (SlickException exception) {
			exception.printStackTrace();
		}
		
		
	}

}
