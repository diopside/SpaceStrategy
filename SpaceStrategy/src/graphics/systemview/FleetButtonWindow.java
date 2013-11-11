package graphics.systemview;

import graphics.FadeButton;

import java.util.ArrayList;

import states.SystemView;

public class FleetButtonWindow extends ButtonWindow {

	public FleetButtonWindow(int x, int y) {
		super(x, y, generateButtons(x, y));
	}

	
	private static ArrayList<FadeButton> generateButtons(int x, int y){
		ArrayList<FadeButton> buttons = new ArrayList<FadeButton>();

		buttons.add( new FadeButton(0, SystemView.BACKGROUND_START_Y + 10, "res/buttons/systemview/fleet0.png", buttons.size()));
		buttons.add( new FadeButton(0, SystemView.BACKGROUND_START_Y + 10, "res/buttons/systemview/fleets1.png", buttons.size()));
		buttons.add( new FadeButton(0, SystemView.BACKGROUND_START_Y + 10, "res/buttons/systemview/fleets2.png", buttons.size()));
		
		return buttons;
	}
}
