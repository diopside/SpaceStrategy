package states;

import org.newdawn.slick.state.StateBasedGame;

public interface ExitableState {

	public void exit(StateBasedGame game);
	
	public void enter(StateBasedGame game);
	
	
}
