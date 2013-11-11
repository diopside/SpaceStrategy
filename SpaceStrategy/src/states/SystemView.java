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
	
	/* 11/11
	 * The behavior of this game state will be greatly dictated by user input
	 * 
	 * The way this behavior unfolds is essentially
	 * 1: The selected planet displayed at the top of the screen will determine the layout of the leftWindow
	 * 2: The leftWindow in turn determines the options of buttons on the rightWindow
	 * 3: The rightWindow button selection then determines what is displayed in the middle window
	 * 
	 * Right now all of the features are layed out in a pretty messy inefficient way with static windows for each individual state of the windows
	 * Along with this the method of changing to the correct windows is dictated by a shitload of switch and if statements
	 * This will most likely be improved upon but I already rewrote this once because I wasn't 100% sure of where I was going with it.  I will wait until I am much closer to 
	 * a finished product before I dedicate more work than necessary. 
	 * 
	 */

	private final int ID;
	public final static short BACKGROUND_START_Y = 175, BUTTON_HEIGHT = 140, RIGHT_WINDOW_Y = 705, LEFT_WINDOW_X = SpaceStrategy.WIDTH - 325;

	private StarSystem sys;
	private int planetIndex; // tracks the current selected planet
	private Image background;
	private ListWindow listWindow; // the center window
	private ArrayList<Listable> tempListableList; // this list will be used to transfer list of classes implementing Listable to the listWindow
	private ButtonWindow leftWindow, rightWindow;
	private Listable listWindowItem;

	private static ButtonWindow playerPlanetBW, factionPlanetBW, unownedPlanetBW; // left button windows
	private static ButtonWindow prodBW, buildBW, defBW, fleetBW, infoBW, groundWarBW, emptyBW, siegeBW;


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
			sys.setPlanetRenderTransparency(planetIndex); // This loop will make all of the unselected planets somewhat transparent, and the selected planet opaque
			for (Planet p: sys.getPlanets()){
				p.render(g, 0, 0);
			}
		}

		background.draw(0, BACKGROUND_START_Y);


		renderWindows(game, g);
		// The renderable interface method render includes offsets, since this screen will not be scrollable both offsets will be 0
		leftWindow.render(g, 0, 0);
		rightWindow.render(g, 0, 0);






	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		Input input = container.getInput();
		int mouseX = input.getAbsoluteMouseX();
		int mouseY = input.getAbsoluteMouseY();

		if (input.isKeyPressed(input.KEY_ESCAPE)){
			// Exit Systemview and return to galaxyview
			GalaxyState gs = (GalaxyState) game.getState(SpaceStrategy.GALAXY_STATE_ID);
			exit(game);
			SpaceStrategy.beep1.play();
			gs.enter(game);
		}

		if (input.isMousePressed(input.MOUSE_LEFT_BUTTON)){ // if the user clicked it will check all clickable entities

			rightWindow.checkIfButtonPressed(mouseX, mouseY); // check right window first because it is dependent on left window


			boolean pressed = leftWindow.checkIfButtonPressed(mouseX, mouseY);
			if (pressed) { // this will reset the center panel whenever the left selection is changed
				clearLists();
			}
			checkPlanets(mouseX, mouseY);
			System.out.println(listWindow.getListItems().size());
			if (listWindow.getListItems().size() > 0){
				int selection = listWindow.checkIfButtonPressed(mouseX, mouseY);
				if (selection != -1){
					listWindowItem = listWindow.getListable(selection);
					listWindow.selectBox(selection);
				}
			}
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
				if (p.getProximityToSun() != planetIndex){
					resetWindowIndices();
					planetIndex = p.getProximityToSun();
					clearLists();
					SpaceStrategy.click1.play();
					return;
				}
			}
		}
	}
	
	private void resetWindowIndices(){
		leftWindow.select(0, false);
		rightWindow.select(0, false);
	}



	private void clearLists(){
		// without clearing the list, unfortunate glitches like ceaselessly expanding listwindow entries can occur
		tempListableList.clear();
		listWindow.clearList();
	}

	private int getPlanetOwnershipStatus(Game world){
		/*
		 * This method will be used to determine the status of the selected planet
		 * if it is owned the played it will return a 0
		 * if it is owned by a computer faction it will return a 1
		 * if it is not currently owned it will return a -1
		 */
		if (sys.getPlanets()[planetIndex].getOwner() == null)
			return -1;
		Planet p = sys.getPlanets()[planetIndex];
		if (p.getOwner().equals(world.getFactions().get(0)))
			return 0;
		return 1;

	}


	private void renderInformation(StateBasedGame game, Graphics g){
		/*
		 * This method will render information on the listWindow menu in the center of the screen
		 * if information is selected from the leftWindow
		 */

		Planet p = sys.getPlanets()[planetIndex];
		g.setFont(SpaceStrategy.GALANT);
		g.setColor(Color.white);

		switch(rightWindow.getSelectedButtonIndex()) {
		
		case 0: // CASE GENERAL INFORMATION
			g.drawString("Planet name: " + p.getName(), 350, BACKGROUND_START_Y + 100 );
			g.drawString("Planet size: " + p.getSize(), 350, BACKGROUND_START_Y + 150 );
			g.drawString("Mineral Abundance: " + p.getMineralRating(), 350, BACKGROUND_START_Y + 200 );
			g.drawString("Biological Diversity: " + p.getBioRating(), 350, BACKGROUND_START_Y + 250 );
			g.drawString("Population: " + p.getPopulation(), 350, BACKGROUND_START_Y + 300 );
			if (p.getOwner() != null)
				g.drawString("Name: " + p.getOwner().toString(), 350, BACKGROUND_START_Y + 350 );
			else
				g.drawString("Name: noone", 350, BACKGROUND_START_Y + 350 );
			break;
			
		case 1: // CASE SPECIALS INFORMATION
			g.drawString("SPECIALS WILL BE DISPLAYED HERE", 350, BACKGROUND_START_Y + 100);
			break;
			
		case 2: // CASE ECONOMY INFORMATION
			g.drawString("ECONOMY WILL BE DISPLAYED HERE", 350, BACKGROUND_START_Y+ 100);
			break;
			
		case 3: // CASE SCIENCE INFORMATION
			g.drawString("SCIENCE WILL BE DISPLAYED HERE", 350, BACKGROUND_START_Y+ 100);
			break;
		case 4: // CASE POPULATION INFORMATION
			g.drawString("POPULATION WILL BE DISPLAYED HERE", 350, BACKGROUND_START_Y+ 100);
			break;


		}




	}
	private void renderProduction(StateBasedGame game, Graphics g){
		listWindow.render(g, 0, 0);
	}
	private void renderBuildings(StateBasedGame game, Graphics g){
		if (tempListableList.size() != getPlanet().getBuildings().size()){ // if the buildings have not been added to the listWindow, add them
			tempListableList.addAll(getPlanet().getBuildings());
			listWindow.setList(tempListableList);
		}
		listWindow.render(g, 0, 0);
	}
	private void renderDefenses(StateBasedGame game, Graphics g){
		listWindow.render(g, 0, 0);
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

		switch (leftWindow.getSelectedButtonIndex()){ // this will determine what should be displayed in the center panel
		case 0: 
			renderInformation(game, g); 
			rightWindow = infoBW;
			break;
		case 1:
			renderProduction(game, g); 
			rightWindow = prodBW;
			break;
		case 2:
			renderBuildings(game, g); 
			rightWindow = buildBW;
			break;
		case 3:
			renderDefenses(game, g); 
			rightWindow = defBW;
			break;
		case 4:
			renderFleet(game, g);
			rightWindow = fleetBW;
			break;
		}



	}

	private void renderUnownedPlanet(StateBasedGame game, Graphics g){
		switch (leftWindow.getSelectedButtonIndex()){ // this will determine what should be displayed in the center panel
		case 0: 
			renderInformation(game, g); 
			rightWindow = infoBW;
			break;
		case 1:
			renderSiege(game, g); 
			rightWindow = prodBW;
			break;
		case 2:
			renderGroundWar(game, g); 
			rightWindow = buildBW;
			break;
		case 3:
			renderDefenses(game, g); 
			rightWindow = defBW;
			break;
		case 4:
			renderFleet(game, g);
			rightWindow = fleetBW;
			break;
		}
	}

	private void renderFactionPlanet(StateBasedGame game, Graphics g){
		switch (leftWindow.getSelectedButtonIndex()){ // this will determine what should be displayed in the center panel
		case 0: 
			renderInformation(game, g); 
			rightWindow = infoBW;
			break;
		case 1:
			renderSiege(game, g); 
			rightWindow = prodBW;
			break;
		case 2:
			renderGroundWar(game, g); 
			rightWindow = buildBW;
			break;
		case 3:
			renderDefenses(game, g); 
			rightWindow = defBW;
			break;
		case 4:
			renderFleet(game, g);
			rightWindow = fleetBW;
			break;
		}
	}


	public void renderWindows(StateBasedGame ga, Graphics g){
		if (sys == null)
			return;

		Game game = ((GalaxyState) ga.getState(SpaceStrategy.GALAXY_STATE_ID)).getWorld();
		switch (getPlanetOwnershipStatus(game)){ // these two lines will determine whether the selected planet is unowned, player owned, or computer owned

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

	}

	public void enter(StateBasedGame game) {
		game.enterState(ID);
		this.unpauseRender();
		this.unpauseUpdate();
		rightWindow = infoBW;



	}

	//******************************************** Static Methods ****************************************************

	private static void initWindows(){
		playerPlanetBW = new PlayerPlanetButtonWindow(0, BACKGROUND_START_Y + 20);
		factionPlanetBW = new FactionPlanetButtonWindow(0, BACKGROUND_START_Y + 20);
		unownedPlanetBW = new UnownedPlanetButtonWindow(0, BACKGROUND_START_Y + 20);
		buildBW = new BuildingButtonWindow(SpaceStrategy.WIDTH - ButtonWindow.getWIDTH(), BACKGROUND_START_Y + 20);
		prodBW = new ProductionButtonWindow(SpaceStrategy.WIDTH - ButtonWindow.getWIDTH(), BACKGROUND_START_Y + 20);
		defBW = new DefensesButtonWindow(SpaceStrategy.WIDTH - ButtonWindow.getWIDTH(), BACKGROUND_START_Y + 20);
		fleetBW = new FleetButtonWindow(SpaceStrategy.WIDTH - ButtonWindow.getWIDTH(), BACKGROUND_START_Y + 20);
		siegeBW = new FleetButtonWindow(SpaceStrategy.WIDTH - ButtonWindow.getWIDTH(), BACKGROUND_START_Y + 20);
		infoBW = new InfoButtonWindow(SpaceStrategy.WIDTH - ButtonWindow.getWIDTH(), BACKGROUND_START_Y + 20);
		emptyBW = new EmptyButtonWindow(SpaceStrategy.WIDTH - ButtonWindow.getWIDTH(), BACKGROUND_START_Y + 20);
	}



}
