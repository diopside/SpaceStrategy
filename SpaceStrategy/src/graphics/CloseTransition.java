package graphics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.Transition;



/*
 * This class will implement the Slick2D transition interface for the SpaceGame.
 * 
 * It will simulate a screen closing from both sides, then opening to reveal the new state.
 * This will be made custom for a screen size 1600 x 900, depending on if I plan on using for other things I may change that
 * 
 * 
 */

public class CloseTransition implements Transition{

	private Image transitionImage, reflectedImage;
	private float renderStart;
	private float timer;
	private boolean closing, playSound;
	private static Sound noise;
	
	public CloseTransition(boolean closing){
		timer = 1.192f;  // This length is tailor made by the length of the sound in seconds
		renderStart = (closing) ? 1 : 800;// if it is closing start rendering at 1 else if it is opening start opening at the final closed position (1/2 screen width)
		playSound = true;
		initImage();
		initSound();
		this.closing = closing;
	}

	private void initImage(){
		try {
			transitionImage = new Image("res/transitions/closetransition.png");
		} catch (SlickException exception) {
			exception.printStackTrace();
		}
	}
	
	private void initSound(){
		try {
			noise = new Sound("res/sounds/transitionclose.wav");
		} catch (SlickException exception) {
			exception.printStackTrace();
		}
	}


	public void init(GameState beforeState, GameState afterState) {

	}

	@Override
	public boolean isComplete() {
		if (closing)
			return renderStart == 800;
		else
			return renderStart <= 0;
	}

	@Override
	public void postRender(StateBasedGame game, GameContainer container,
			Graphics g) throws SlickException {

		Image tempImage = transitionImage.getSubImage((int)(transitionImage.getWidth() - renderStart), 0 , (int) renderStart, transitionImage.getHeight());
		reflectedImage = tempImage.copy();
		reflectedImage.rotate(180f);

		tempImage.draw(0, 0);
		reflectedImage.draw(1600 - renderStart, 0);

	}

	@Override
	public void preRender(StateBasedGame game, GameContainer container,
			Graphics g) throws SlickException {

	}

	@Override
	public void update(StateBasedGame game, GameContainer container, int delta)
			throws SlickException {
		if (playSound) {
			/*
			 * This is added both to choose to disable the sound entirely and so that it doesn't repeatedly play sound each update
			 */
			noise.play();
			playSound = false;
		}
		
		if (closing){
			renderStart += delta * (1.0f/ (float) timer);
			if (renderStart > 800)
				renderStart = 800;
		}
		else {
			renderStart -= delta * (1.0f/ (float) timer);
			if (renderStart < 0)
				renderStart = 0;
		}
	}

}








