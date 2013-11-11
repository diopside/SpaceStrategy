package graphics.systemview;

import graphics.FadeButton;

import java.util.ArrayList;

import states.SystemView;

public class FactionPlanetButtonWindow extends ButtonWindow {

	public FactionPlanetButtonWindow(int x, int y) {
		super(x, y, FactionPlanetButtonWindow.generateButtons());
	}
	
	
	private static ArrayList<FadeButton> generateButtons(){
		ArrayList<FadeButton> buttons = new ArrayList<FadeButton>();
		buttons.add( new FadeButton(0, SystemView.BACKGROUND_START_Y + 10, "res/buttons/systemview/informationbtn.png", buttons.size()));
		buttons.add( new FadeButton(0, SystemView.BACKGROUND_START_Y + 10 + SystemView.BUTTON_HEIGHT, "res/buttons/systemview/siegebtn.png", buttons.size()));
		buttons.add( new FadeButton(0, SystemView.BACKGROUND_START_Y + 10 + SystemView.BUTTON_HEIGHT * 2, "res/buttons/systemview/groundwarbtn.png", buttons.size()));
		buttons.add( new FadeButton(0, SystemView.BACKGROUND_START_Y + 10 + SystemView.BUTTON_HEIGHT * 3, "res/buttons/systemview/defensesbtn.png", buttons.size()));
		buttons.add( new FadeButton(0, SystemView.BACKGROUND_START_Y + 10 + SystemView.BUTTON_HEIGHT, "res/buttons/systemview/fleetbtn.png", buttons.size()));
		
		return buttons;
	}

}
