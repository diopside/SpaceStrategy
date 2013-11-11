package graphics.systemview;

import graphics.FadeButton;

import java.util.ArrayList;

import states.SystemView;

public class DefensesButtonWindow extends ButtonWindow {

	public DefensesButtonWindow(int x, int y) {
		super(x, y, generateButtons());
	}
	
	
	private static ArrayList<FadeButton> generateButtons(){
		ArrayList<FadeButton> buttons = new ArrayList<FadeButton>();
		buttons.add( new FadeButton(0, SystemView.BACKGROUND_START_Y + 10, "res/buttons/systemview/def0.png", buttons.size()));
		buttons.add( new FadeButton(0, SystemView.BACKGROUND_START_Y + 10, "res/buttons/systemview/def1.png", buttons.size()));
		
		// defenses shares a set activity button so it copies build1.png
		buttons.add( new FadeButton(0, SystemView.BACKGROUND_START_Y + 10, "res/buttons/systemview/build1.png", buttons.size()));
		
		return buttons;
	}

}
