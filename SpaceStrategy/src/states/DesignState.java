package states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class DesignState extends BasicGameState implements ExitableState{


	private final int ID;

	public DesignState(int id){
		ID = id;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

	}



	//*************************** Game State Methods *********************************************************
	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics g) throws SlickException {

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		
		Input input = container.getInput();
		
		if (input.isKeyPressed(input.KEY_ESCAPE)){

			exit(game);
		}
		

		input.clearKeyPressedRecord();
	}

	@Override
	public int getID() {
		return ID;
	}

	// *********************************** General Methods **************************************************




	// ********************************************* Interface Methods *****************************************
	@Override
	public void exit(StateBasedGame game) {
		GalaxyState gS = (GalaxyState) game.getState(SpaceStrategy.GALAXY_STATE_ID);
		gS.closeEnter(game);
	}

	@Override
	public void enter(StateBasedGame game) {

	}
}
