package states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entities.Faction;
import entities.Game;

public class EndTurnState extends BasicGameState implements ExitableState {
	
	private final int ID;
	private Game world;
	
	public EndTurnState(int id){
		ID = id;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		world = ((GalaxyState) game.getState(SpaceStrategy.GALAXY_STATE_ID)).getWorld();
	}

	
	// ********************************** GameState Methods ************************************************
	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics g) throws SlickException {
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
	
		
		world.endTurn(game);
		
		exit(game);
		

	}

	@Override
	public int getID() {
		return ID;
	}

	
	
	// ********************************************** Interface Methods ************************************
	@Override
	public void exit(StateBasedGame game) {
		GalaxyState gS = (GalaxyState) game.getState(SpaceStrategy.GALAXY_STATE_ID);
		gS.closeEnter(game);
	}

	@Override
	public void enter(StateBasedGame game) {
		
	}

}





