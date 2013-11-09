package states;

import java.util.Stack;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entities.Game;
import entities.StarSystem;
import graphics.CloseTransition;
import graphics.GalaxyMenu;

public class GalaxyState extends BasicGameState implements ExitableState {

	
	//******************* Final and Static Members ******************************
	private final int ID;
	private static final int SCROLL_SPEED = 8, GALAXY_MENU_X = 0, GALAXY_MENU_Y = SpaceStrategy.HEIGHT - 240;
	
	//************************ Members ************************
	private Image galaxyBackground;
	private int xOffset, yOffset;
	private GalaxyMenu galaxyMenu;
	private Game world;
	
	private StarSystem[] systems;
	
	//*******************************************************Constructors and initialization methods***************************************
	public GalaxyState(int id){
		ID = id;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		initImages();
		world = new Game(4);
		
		systems = world.getSystems();
		galaxyMenu = new GalaxyMenu(GALAXY_MENU_X, GALAXY_MENU_Y, "res/menus/hud.png");
		
	}
	
	private void initImages(){
		try {
			galaxyBackground = new Image("res/backgrounds/galaxybackground.png");
		} catch (SlickException exception) {
			exception.printStackTrace();
		}
	}
	
	//****************************************************Basic Game State Methods**********************************************
	

	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics g) throws SlickException {
		galaxyBackground.getSubImage(xOffset, yOffset, SpaceStrategy.WIDTH, SpaceStrategy.HEIGHT).draw();
		for (StarSystem sys: systems){
			if (onScreen(sys.getShape())){
				g.setColor(sys.getSystemColor());
				sys.render(g, xOffset, yOffset);
			}
	
		} // starsystem render loop
		galaxyMenu.render(g, xOffset, yOffset);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();
		int mouseX = input.getAbsoluteMouseX();
		int mouseY = input.getAbsoluteMouseY();
		System.out.println(systems[0].getName());
		System.out.println(world.getFactions().get(0).getCOLOR());
		System.out.println(systems[0].getNumberOfFactions());
		
		
		if (input.isKeyDown(input.KEY_A))
			if (xOffset >= - 250)
				xOffset -= SCROLL_SPEED;
		if (input.isKeyDown(input.KEY_D))
			if (xOffset + SpaceStrategy.WIDTH <= galaxyBackground.getWidth() + 250)
				xOffset += SCROLL_SPEED;
		if (input.isKeyDown(input.KEY_W))
			if (yOffset >= - 250)
				yOffset -= SCROLL_SPEED;
		if (input.isKeyDown(input.KEY_S))
			if (yOffset + SpaceStrategy.HEIGHT <= galaxyBackground.getHeight() + 250)
				yOffset += SCROLL_SPEED;

		if (input.isMousePressed(input.MOUSE_LEFT_BUTTON)){
			if (galaxyMenu.contains(mouseX, mouseY))
				galaxyMenu.checkMenus(mouseX, mouseY, game);
			else
				checkIfSystemSelected(mouseX + xOffset, mouseY + yOffset, game);

		}


		input.clearKeyPressedRecord();
	}

	@Override
	public int getID() {
		return ID;
	}

	//**************************************************************************************************************************


	//****************************Interface Methods*******************************************************************************
	@Override
	public void exit(StateBasedGame game) {
		this.pauseUpdate();
		this.pauseRender();
	}
	@Override
	public void enter(StateBasedGame game) {
		game.enterState(ID);
		this.unpauseRender();
		this.unpauseUpdate();
		
	}
	
	public void closeEnter(StateBasedGame game){
		game.enterState(ID, new CloseTransition(true), new CloseTransition(false));
		this.unpauseRender();
		this.unpauseUpdate();
	}
	
	//**************************************************************************************************************************
	
	//******************************** General Methods ***************************************************************************8
	
	public Game getWorld(){
		return world;
	}
	
	public boolean onScreen(Shape s){
		return s.intersects(new Rectangle(xOffset, yOffset, SpaceStrategy.WIDTH, SpaceStrategy.HEIGHT));
	}
	
	
	private void checkIfSystemSelected(int mouseX, int mouseY, StateBasedGame game){
		for (StarSystem sys: systems){
			if (sys.contains(mouseX, mouseY)){
				SpaceStrategy.click1.play();
				exit(game);
				SystemView sV = (SystemView) game.getState(SpaceStrategy.SYSTEM_VIEW_ID);
				sV.setSelectedSystem(sys);
				sV.enter(game);
			}
		}
	}
	
	
	
	

	
	
	//*****************************************Static Methods********************************************************************

	
	
	
}






