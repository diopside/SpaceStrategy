package states;

import java.util.ArrayList;

import entities.Faction;
import entities.Game;
import graphics.FadeButton;
import graphics.listwindows.ListWindow;
import graphics.listwindows.ShipSelectionListWindow;
import graphics.systemview.ButtonWindow;
import graphics.systemview.FleetCreationButtonWindow;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class FleetCreationState extends BasicGameState implements ExitableState{
	// This class will be accessed from planets to construct fleets


	private final int ID;
	/*
	 * Name Fleet, Fleet Type, Add Ships, Add Leader, StarSystem
	 */
	private ButtonWindow leftWindow;
	private ListWindow centerWindow;
	private Image background;
	private Faction player;
	private boolean[] selections;
	private int oldLeftIndex;

	public FleetCreationState(int id){
		ID = id;


	}



	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		leftWindow = new FleetCreationButtonWindow(0, SystemView.BACKGROUND_START_Y);
		background = new Image("res/menus/systemview/background.png");
		Game world = ((GalaxyState) game.getState(SpaceStrategy.GALAXY_STATE_ID)).getWorld();
		player = world.getFactions().get(0);
		centerWindow = null;
		
	}




	//********************************* BasicGameState methods **************************************
	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics g) throws SlickException {
		background.draw(0, SystemView.BACKGROUND_START_Y);
		leftWindow.render(g, 0, 0);


		setCenterWindow(leftWindow.getSelectedButtonIndex());
		if (centerWindow != null){
			centerWindow.render(g, 0, 0);
		}

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		Input input = container.getInput();
		int mouseX = input.getMouseX(); int mouseY = input.getMouseY();

		if (input.isKeyPressed(input.KEY_ESCAPE)){
			exit(game);
			SpaceStrategy.beep1.play();
		}

		if (input.isMousePressed(input.MOUSE_LEFT_BUTTON)){
			leftWindow.checkIfButtonPressed(mouseX, mouseY);

			if (centerWindow != null && centerWindow.getListItems().size() > 0){
				int selection = centerWindow.checkIfButtonPressed(mouseX, mouseY);
				if (selection != -1){
					centerWindow.selectBox(selection);
				}
				else if (selection >= 0 && selection < 8){

				}
			}
		}


		input.clearKeyPressedRecord();

	}

	@Override
	public int getID() {
		return ID;
	}

	//************************************* General Methods *****************************

	private void setCenterWindow(int index){


		switch (index) {
		case 0: // Name Fleet

			break;

		case 1: // Select Mission

			break;

		case 2: // Select Leader

			break;

		case 3: // Select Ships
			if (oldLeftIndex != index)
				centerWindow = new ShipSelectionListWindow(SystemView.LIST_WINDOW_START_X, SystemView.BACKGROUND_START_Y, player.getReserveShips(), selections);
			break;

		case 4: // Select StarSystem

			break;


			

		} // end switch
		oldLeftIndex = index;

	}






	//*********************************** Interface Methods******************************

	@Override
	public void exit(StateBasedGame game) {
		centerWindow = null;
		leftWindow.select(0, false);
		SystemView sv = (SystemView) game.getState(SpaceStrategy.SYSTEM_VIEW_ID);
		sv.setRightWindowIndex(0); // if this doesn't happen the next time the player selects fleet it will automatically return them to this window

		game.enterState(SpaceStrategy.SYSTEM_VIEW_ID);
	}

	@Override
	public void enter(StateBasedGame game) {

		selections = new boolean[player.getReserveShips().size()];
		game.enterState(ID);
		oldLeftIndex = -1;

	}























}
