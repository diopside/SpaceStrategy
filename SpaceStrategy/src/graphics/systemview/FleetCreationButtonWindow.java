package graphics.systemview;

import graphics.FadeButton;

import java.util.ArrayList;

public class FleetCreationButtonWindow extends ButtonWindow{

	public FleetCreationButtonWindow(int x, int y) {
		super(x, y + 10, generateButtons(x, y + 10));
	}
	
	
	private static ArrayList<FadeButton> generateButtons(int x, int y){
		ArrayList<FadeButton> buttons = new ArrayList<>();
		buttons.add(new FadeButton(x, y, "res/buttons/fleetcreation/namefleet.png", buttons.size()));
		buttons.add(new FadeButton(x, y, "res/buttons/fleetcreation/selectmission.png", buttons.size()));
		buttons.add(new FadeButton(x, y, "res/buttons/fleetcreation/selectleader.png", buttons.size()));
		buttons.add(new FadeButton(x, y, "res/buttons/fleetcreation/selectships.png", buttons.size()));
		buttons.add(new FadeButton(x, y, "res/buttons/fleetcreation/selectstarsystem.png", buttons.size()));
		
		return buttons;
	}

}
