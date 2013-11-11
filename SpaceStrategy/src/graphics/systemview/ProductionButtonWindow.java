package graphics.systemview;

import graphics.FadeButton;

import java.util.ArrayList;

import states.SystemView;

public class ProductionButtonWindow extends ButtonWindow {

	public ProductionButtonWindow(int x, int y) {
		super(x, y, generateButtons());
	}
	
	private static ArrayList<FadeButton> generateButtons(){
		ArrayList<FadeButton> buttons = new ArrayList<FadeButton>();
		
		buttons.add( new FadeButton(0, SystemView.BACKGROUND_START_Y + 10, "res/buttons/systemview/prod0.png", buttons.size()));
		buttons.add( new FadeButton(0, SystemView.BACKGROUND_START_Y + 10, "res/buttons/systemview/prod1.png", buttons.size()));
		buttons.add( new FadeButton(0, SystemView.BACKGROUND_START_Y + 10, "res/buttons/systemview/prod2.png", buttons.size()));
		buttons.add( new FadeButton(0, SystemView.BACKGROUND_START_Y + 10, "res/buttons/systemview/prod3.png", buttons.size()));
		buttons.add( new FadeButton(0, SystemView.BACKGROUND_START_Y + 10, "res/buttons/systemview/prod4.png", buttons.size()));
		
		return buttons;
	}

}
