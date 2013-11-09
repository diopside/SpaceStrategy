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

public class SystemView extends BasicGameState implements ExitableState {

	private final int ID;
	public final static int BACKGROUND_START_Y = 175, BUTTON_HEIGHT = 140;

	private StarSystem sys;
	private int planetIndex, buttonIndex;
	private Image background;
	private FadeButton[] buttons;
	private ListWindow listWindow;
	private ArrayList<Listable> tempListableList;


	private static FadeButton infoBtn, prodBtn, bldBtn, defBtn, popBtn, fleetBtn, siegeBtn, groundWarBtn;



	//*******************************************************Constructors and initialization methods***************************************
	public SystemView(int id){
		ID = id;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

		initImages();
		initStaticButtons();
		listWindow = new ListWindow(301, BACKGROUND_START_Y + 10);
		buttons = new FadeButton[5];
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

		for(FadeButton btn: buttons){
			btn.render(g, 0, 0);
		}



		if (sys != null){
			switch (getPlanetOwnershipStatus(((GalaxyState) game.getState(SpaceStrategy.GALAXY_STATE_ID)).getWorld())) {

			case 0 : 
				renderOwnedPlanet(game, g);
				break;
			case 1: 
				renderFactionPlanet(game, g);
				break;

			default: 
				renderUnownedPlanet(game, g); 
				break;
			}
		}




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
			checkButtons(mouseX, mouseY);
			checkPlanets(mouseX, mouseY);
		}


		setButtons(((GalaxyState) game.getState(SpaceStrategy.GALAXY_STATE_ID)).getWorld());
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

	private void checkButtons(int mouseX, int mouseY){
		for (int i = 0; i < buttons.length; i ++){
			if (buttonIndex - 1 != i && buttons[i].contains(mouseX, mouseY)){
				select(i);

				SpaceStrategy.click1.play();
			}
		}
	}

	private void checkPlanets(int mouseX, int mouseY){
		for (Planet p: sys.getPlanets()){
			if (p.contains(mouseX, mouseY)){
				if (p.getProximityToSun() != planetIndex){ // this will reset the button position if you are selecting a new planet
					select(0);
				}
				planetIndex = p.getProximityToSun();
				clearLists();
				SpaceStrategy.click1.play();
				return;
			}
		}
	}

	private void select(int index){
		for (FadeButton b: buttons)
			b.setAlpha(.5f);
		buttons[index].setAlpha(1f);
		buttonIndex = index + 1;
		
		
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

	private void setButtons(Game world){
		if (sys == null)
			return;
		int status = getPlanetOwnershipStatus(world);
		if (status == 0){
			buttons[0] = infoBtn;
			buttons[1] = prodBtn;
			buttons[2] = bldBtn;
			buttons[3] = defBtn;
			buttons[4] = popBtn;
		}
		else {
			buttons[0] = infoBtn;
			buttons[1] = fleetBtn;
			buttons[2] = siegeBtn;
			buttons[3] = defBtn;
			buttons[4] = groundWarBtn;
		}

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
		switch (buttonIndex) {
		case 1:
			renderInformation(game, g); 
			break;

		case 2:
			renderProduction(game, g);
			break;

		case 3: 
			renderBuildings(game, g); 
			break;

		case 4: 
			renderDefenses(game, g); 
			break;

		case 5: 
			renderPopulation(game, g); 
			break;

		default:
			break;
		}
	}

	private void renderFactionPlanet(StateBasedGame game, Graphics g){
		switch (buttonIndex) {
		case 1:
			renderInformation(game, g); 
			break;

		case 2:
			renderFleet(game, g);
			break;

		case 3: 
			renderSiege(game, g); 
			break;

		case 4: 
			renderDefenses(game, g); 
			break;

		case 5: 
			renderGroundWar(game, g); 
			break;

		default:
			break;
		}

	}

	private void renderUnownedPlanet(StateBasedGame game, Graphics g){
		switch (buttonIndex) {
		case 1:
			renderInformation(game, g); 
			break;

		case 2:
			renderFleet(game, g);
			break;

		case 3: 
			renderSiege(game, g); 
			break;

		case 4: 
			renderDefenses(game, g); 
			break;

		case 5: 
			renderGroundWar(game, g); 
			break;

		default:
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

		select(0);
	}

	public void enter(StateBasedGame game) {
		game.enterState(ID);
		setButtons(((GalaxyState) game.getState(SpaceStrategy.GALAXY_STATE_ID)).getWorld());
		this.unpauseRender();
		this.unpauseUpdate();
	}


	// *********************************************** static members ***************************************************
	private static void initStaticButtons(){
		infoBtn = new FadeButton(0, BACKGROUND_START_Y + 10, "res/buttons/systemview/informationbtn.png");
		prodBtn = new FadeButton(0, BACKGROUND_START_Y + 10 + BUTTON_HEIGHT, "res/buttons/systemview/productionbtn.png");
		bldBtn = new FadeButton(0, BACKGROUND_START_Y + 10 + BUTTON_HEIGHT * 2, "res/buttons/systemview/buildingsbtn.png");
		defBtn = new FadeButton(0, BACKGROUND_START_Y + 10 + BUTTON_HEIGHT * 3, "res/buttons/systemview/defensesbtn.png");
		popBtn = new FadeButton(0, BACKGROUND_START_Y + 10 + BUTTON_HEIGHT * 4, "res/buttons/systemview/populationbtn.png");
		fleetBtn = new FadeButton(0, BACKGROUND_START_Y + 10 + BUTTON_HEIGHT, "res/buttons/systemview/fleetbtn.png");
		siegeBtn = new FadeButton(0, BACKGROUND_START_Y + 10 + BUTTON_HEIGHT * 2, "res/buttons/systemview/siegebtn.png");
		groundWarBtn = new FadeButton(0, BACKGROUND_START_Y + 10 + BUTTON_HEIGHT * 4, "res/buttons/systemview/groundwarbtn.png");
	}



}
