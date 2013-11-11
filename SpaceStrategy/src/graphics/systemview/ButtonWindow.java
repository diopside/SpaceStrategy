package graphics.systemview;

import graphics.FadeButton;
import graphics.Renderable;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import states.SpaceStrategy;
import states.SystemView;

abstract public class ButtonWindow implements Renderable{

	// ******************************* static and final members
	private static Image WINDOW;
	private static final short SCROLL_X = 302, SCROLL_UP_Y = 2, SCROLL_DOWN_Y  = 671, SCROLL_SIZE_X = 21, SCROLL_SIZE_Y = 29, WIDTH = 325;

	// *********************************** General Members
	private int x, y;
	private ArrayList<FadeButton> buttons;
	private Rectangle scrollUp, scrollDown;
	private int buttonRenderIndex, selectedButtonIndex;

	//********************************************* Constructors and Initialization Methods ***************************************
	public ButtonWindow(int x, int y, ArrayList<FadeButton> buttons){
		this.x = x;
		this.y = y;
		initImage();
		this.buttons = buttons;

		scrollUp = new Rectangle(x + SCROLL_X, y + SCROLL_UP_Y, SCROLL_SIZE_X, SCROLL_SIZE_Y);
		scrollDown = new Rectangle(x + SCROLL_X, y + SCROLL_DOWN_Y, SCROLL_SIZE_X, SCROLL_SIZE_Y);
	}

	private void initImage(){
		try {
			WINDOW = new Image("res/menus/systemview/buttonwindow.png");
		} catch (SlickException exception) {
			exception.printStackTrace();
		}
	}

	//******************************************************* General Methods ************************************************


	public boolean checkIfButtonPressed(int mouseX, int mouseY){
		
		// Essentially everything that happens here is explained in the render method below because Developer.isLazy() == true;
		int index = buttonRenderIndex;
		for (int numChecked = 0; (numChecked < 5) && (numChecked < buttons.size()); numChecked ++){
			if (buttons.get(index).contains(mouseX, mouseY)){
				select(index);
				return true;
			}
			index = (index + 1 >= buttons.size()) ? 0 : index + 1;
		}
		return false;
	}

	public void select(int buttonID, boolean b){
		
		// this block will make it so only the selected button has full alpha
		for (FadeButton btn: buttons){
			btn.setAlpha(.5f);
		}
		buttons.get(buttonID).setAlpha(1f);
		
		
		if (b) // there are some instances where selections change without the user clicking, in that instance I don't want the click noise to play
			SpaceStrategy.click1.play();
		selectedButtonIndex = buttonID;
	}

	public void select(int buttonID){
		select(buttonID, true);
	}

	public int getSelectedButtonIndex(){
		return selectedButtonIndex;
	}
	



	// ************************************** Interface Methods ****************************************************************
	@Override
	public void render(Graphics g, int xOffset, int yOffset) {
		WINDOW.draw(x, y);

		
		// This next bit will render each button up until 5 are rendered or until each one has been rendered already
		int index = buttonRenderIndex;
		for (int numRendered = 0; (numRendered < 5) && (numRendered < buttons.size()) ; numRendered ++){
			buttons.get(index).setLoc(x, y + numRendered * SystemView.BUTTON_HEIGHT); // rendering will be done based off of the buttons x y coordinates itself, so they must be changed in case scrolling has happened
			buttons.get(index).render(g, 0, 0);

			index = (index + 1 >= buttons.size()) ? 0 : index + 1; // if it doesn't start at 0 it might overflow so it must be accounted for
		}

	}




	@Override
	public Shape getShape() {
		return null;
	}

	public static int getWIDTH(){
		return WIDTH;
	}




}
