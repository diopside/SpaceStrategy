package graphics.systemview;

import graphics.FadeButton;

import java.util.ArrayList;

import states.SystemView;

public class SiegeButtonWindow extends ButtonWindow {

	public SiegeButtonWindow(int x, int y) {
		super(x, y, generateButtons());
	}

	
	private static ArrayList<FadeButton> generateButtons(){
		ArrayList<FadeButton> buttons = new ArrayList<FadeButton>();

		buttons.add( new FadeButton(0, SystemView.BACKGROUND_START_Y + 10, "res/buttons/systemview/siege0.png", buttons.size()));
		buttons.add( new FadeButton(0, SystemView.BACKGROUND_START_Y + 10, "res/buttons/systemview/siege1.png", buttons.size()));
		buttons.add( new FadeButton(0, SystemView.BACKGROUND_START_Y + 10, "res/buttons/systemview/siege2.png", buttons.size()));
		
		return buttons;
	}
}
