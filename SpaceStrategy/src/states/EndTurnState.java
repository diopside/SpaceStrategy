package states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class EndTurnState extends BasicGameState implements ExitableState {
	
	private final int ID;
	
	public EndTurnState(int id){
		ID = id;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
	}

	
	// ********************************** GameState Methods ************************************************
	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics g) throws SlickException {
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		
	}

	@Override
	public int getID() {
		return ID;
	}

	
	
	// ********************************************** Interface Methods ************************************
	@Override
	public void exit(StateBasedGame game) {
		
	}

	@Override
	public void enter(StateBasedGame game) {
		
	}

}
