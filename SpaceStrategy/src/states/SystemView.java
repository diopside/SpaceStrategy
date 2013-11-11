package states;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entities.Faction;
import entities.Game;
import entities.Planet;
import entities.StarSystem;
import graphics.FadeButton;
import graphics.ListWindow;
import graphics.Listable;
import graphics.systemview.*;

public class SystemView extends BasicGameState implements ExitableState {

	private final int ID;
	public final static short BACKGROUND_START_Y = 175, BUTTON_HEIGHT = 140, RIGHT_WINDOW_Y = 705, LEFT_WINDOW_X = SpaceStrategy.WIDTH - 325;

	private StarSystem sys;
	private int planetIndex;
	private Image background;
	private ListWindow listWindow;
	private ArrayList<Listable> tempListableList;
	private ButtonWindow leftWindow, rightWindow;

	private static ButtonWindow playerPlanetBW, factionPlanetBW, unownedPlanetBW;


	//*******************************************************Constructors and initialization methods***************************************
	public SystemView(int id){
		ID = id;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

		SystemView.initWindows();
		initImages();
		listWindow = new ListWindow(326, BACKGROUND_START_Y + 10);
		tempListableList = new ArrayList<Listable>();

	}

	private void initImages(){
		try {
			background = new Image("res/menus/systemview/background.png");
		} catch (SlickException exception) {
			exception.printStackTrace();
		}
	}



	//*********************************Game State Methods*******************************************************

	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics g) throws SlickException {

		if (sys != null)
		{
			sys.setPlanetRenderTransparency(planetIndex);
			for (Planet p: sys.getPlanets()){
				p.render(g, 0, 0);
			}
		}

		background.draw(0, BACKGROUND_START_Y);


		renderWindows(game, g);
		leftWindow.render(g, 0, 0);
		//rightWindow.render(g, 0, 0);
		
		




	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		Input input = container.getInput();
		int mouseX = input.getAbsoluteMouseX();
		int mouseY = input.getAbsoluteMouseY();

		System.out.println(mouseX + "   " + mouseY);

		if (input.isKeyPressed(input.KEY_ESCAPE)){
			GalaxyState gs = (GalaxyState) game.getState(SpaceStrategy.GALAXY_STATE_ID);
			exit(game);
			SpaceStrategy.beep1.play();
			gs.enter(game);
		}

		if (input.isMousePressed(input.MOUSE_LEFT_BUTTON)){
			boolean pressed = leftWindow.checkIfButtonPressed(mouseX, mouseY);
			if (pressed) {
				clearLists();
			}
			//rightWindow.checkButtons(mouseX, mouseY);
			checkPlanets(mouseX, mouseY);
		}

		input.clearKeyPressedRecord();
		

	}

	@Override
	public int getID() {
		return ID;
	}


	//*************************************************General Methods***********************************************
	public void setSelectedSystem(StarSystem sys){
		this.sys = sys;
	}

	public Planet getPlanet(){
		if (sys == null)
			return null;
		return sys.getPlanets()[planetIndex];
	}


	private void checkPlanets(int mouseX, int mouseY){
		for (Planet p: sys.getPlanets()){
			if (p.contains(mouseX, mouseY)){
				
				planetIndex = p.getProximityToSun();
				clearLists();
				SpaceStrategy.click1.play();
				return;
			}
		}
	}


	
	private void clearLists(){
		tempListableList.clear();
		listWindow.clearList();
	}

	private int getPlanetOwnershipStatus(Game world){
		if (sys.getPlanets()[planetIndex].getOwner() == null)
			return -1;
		Planet p = sys.getPlanets()[planetIndex];
		if (p.getOwner().equals(world.getFactions().get(0)))
			return 0;
		return 1;

	}


	private void renderInformation(StateBasedGame game, Graphics g){

		Planet p = sys.getPlanets()[planetIndex];
		g.setFont(SpaceStrategy.GALANT);
		g.setColor(Color.white);

		g.drawString("Planet name: " + p.getName(), 350, BACKGROUND_START_Y + 100 );

		g.drawString("Planet size: " + p.getSize(), 350, BACKGROUND_START_Y + 150 );

		g.drawString("Mineral Abundance: " + p.getMineralRating(), 350, BACKGROUND_START_Y + 200 );

		g.drawString("Biological Diversity: " + p.getBioRating(), 350, BACKGROUND_START_Y + 250 );

		g.drawString("Population: " + p.getPopulation(), 350, BACKGROUND_START_Y + 300 );

		if (p.getOwner() != null)
			g.drawString("Name: " + p.getOwner().toString(), 350, BACKGROUND_START_Y + 350 );
		else
			g.drawString("Name: noone", 350, BACKGROUND_START_Y + 350 );



	}
	private void renderProduction(StateBasedGame game, Graphics g){
		listWindow.render(g, 0, 0);
	}
	private void renderBuildings(StateBasedGame game, Graphics g){
		if (tempListableList.size() == 0){
			tempListableList.addAll(getPlanet().getBuildings());
			listWindow.setList(tempListableList);
		}
		listWindow.render(g, 0, 0);
	}
	private void renderDefenses(StateBasedGame game, Graphics g){
		listWindow.render(g, 0, 0);
	}
	private void renderPopulation(StateBasedGame game, Graphics g){

	}

	private void renderFleet(StateBasedGame game, Graphics g){
		listWindow.render(g, 0, 0);
	}

	private void renderSiege(StateBasedGame game, Graphics g){
		listWindow.render(g, 0, 0);
	}

	private void renderGroundWar(StateBasedGame game, Graphics g){

	}

	private void renderOwnedPlanet(StateBasedGame game, Graphics g){
	
		switch (leftWindow.getSelectedButtonIndex()){
		case 0: 
			renderInformation(game, g); 
			break;
		case 1:
			renderProduction(game, g); 
			break;
		case 2:
			renderBuildings(game, g); 
			break;
		case 3:
			renderDefenses(game, g); 
			
			break;
		case 4:
			renderFleet(game, g);
			
			break;
		}
		
		
		
	}
	
	private void renderUnownedPlanet(StateBasedGame game, Graphics g){
		
	}
	
	private void renderFactionPlanet(StateBasedGame game, Graphics g){
		
	}


	public void renderWindows(StateBasedGame ga, Graphics g){
		if (sys == null)
			return;
		Game game = ((GalaxyState) ga.getState(SpaceStrategy.GALAXY_STATE_ID)).getWorld();
		switch (getPlanetOwnershipStatus(game)){

		case 0:
			leftWindow = playerPlanetBW;
			renderOwnedPlanet(ga, g);
			break;

		case 1:
			leftWindow = factionPlanetBW;

			renderFactionPlanet(ga, g);
			break;
		default:
			leftWindow = unownedPlanetBW;
			renderUnownedPlanet(ga, g);
			break;
		}
		
		

	}


	//**********************************************Interface Methods**********************************************
	@Override
	public void exit(StateBasedGame game) {
		this.pauseRender();
		this.pauseUpdate();
		sys = null;
		planetIndex = 0;

		//select(0);
	}

	public void enter(StateBasedGame game) {
		game.enterState(ID);
		this.unpauseRender();
		this.unpauseUpdate();
	}

	//******************************************** Static Methods ****************************************************
	
	private static void initWindows(){
		playerPlanetBW = new PlayerPlanetButtonWindow(0, BACKGROUND_START_Y + 20);
		factionPlanetBW = new FactionPlanetButtonWindow(0, BACKGROUND_START_Y + 20);
		unownedPlanetBW = new UnownedPlanetButtonWindow(0, BACKGROUND_START_Y + 20);
	}



}
