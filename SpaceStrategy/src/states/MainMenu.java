package states;

import entities.Planet;
import entities.StarSystem;
import graphics.BasicButton;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenu extends BasicGameState {



	private final int ID;
	private static final int GAME_X = 300, GAME_Y = 100, QUIT_X = SpaceStrategy.WIDTH - GAME_X, QUIT_Y = 100;
	private Image background, cursor;
	private BasicButton gameBtn, quitBtn;


	public MainMenu(int id){
		ID = id;
		
	}
	private void initImage(){
		try {
			background = new Image("res/backgrounds/mainmenu.jpg");
			background = background.getScaledCopy(SpaceStrategy.WIDTH, SpaceStrategy.HEIGHT);
			Image cursor = new Image("res/cursors/cursor.png");
		} catch (SlickException exception) {
			exception.printStackTrace();
		}
		
		
	}


	//****************************************************Basic Game State Methods**********************************************
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		initImage();
		
		Image cursor = new Image("res/cursors/cursor.png");
		cursor.setAlpha(.75f);
		container.setMouseCursor(cursor,1,1);
		
		container.getGraphics().setFont(SpaceStrategy.NEUROPOL);
		
		gameBtn = new BasicButton(GAME_X, GAME_Y, "res/buttons/newgamebtn.png");
		quitBtn = new BasicButton(QUIT_X, QUIT_Y, "res/buttons/quitgamebtn.png");
		
		
		//Initialize static image assets
		
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics g) throws SlickException {

		background.draw();
		
		gameBtn.render(g, 0, 0);
		quitBtn.render(g, 0, 0);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
	
		Input input = container.getInput();
		int mouseX = input.getAbsoluteMouseX();
		int mouseY = input.getAbsoluteMouseY();

		if (input.isMousePressed(input.MOUSE_LEFT_BUTTON))
			if (gameBtn.contains(mouseX, mouseY)){
				GalaxyState gs = (GalaxyState) game.getState(SpaceStrategy.GALAXY_STATE_ID);
				gs.enter(game);
			}
			else if (gameBtn.contains(mouseX, mouseY))
				container.exit();






	}

	@Override
	public int getID() {
		return ID;
	}

	//**************************************************************************************************************************

}









