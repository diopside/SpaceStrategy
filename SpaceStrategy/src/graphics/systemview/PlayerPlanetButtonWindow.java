package graphics.systemview;

import graphics.FadeButton;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

import states.SystemView;

public class PlayerPlanetButtonWindow extends ButtonWindow {

	public PlayerPlanetButtonWindow(int x, int y) {
		super(x, y, PlayerPlanetButtonWindow.generateButtons());
	}
	
	
	
	
	
	
	
	
	private static ArrayList<FadeButton> generateButtons(){
		ArrayList<FadeButton> buttons = new ArrayList<FadeButton>();
		buttons.add( new FadeButton(0, SystemView.BACKGROUND_START_Y + 10, "res/buttons/systemview/informationbtn.png", 0));
		buttons.add( new FadeButton(0, SystemView.BACKGROUND_START_Y + 10 + SystemView.BUTTON_HEIGHT, "res/buttons/systemview/productionbtn.png", 1));
		buttons.add( new FadeButton(0, SystemView.BACKGROUND_START_Y + 10 + SystemView.BUTTON_HEIGHT * 2, "res/buttons/systemview/buildingsbtn.png", 2));
		buttons.add( new FadeButton(0, SystemView.BACKGROUND_START_Y + 10 + SystemView.BUTTON_HEIGHT * 3, "res/buttons/systemview/defensesbtn.png", 3));
		buttons.add( new FadeButton(0, SystemView.BACKGROUND_START_Y + 10 + SystemView.BUTTON_HEIGHT, "res/buttons/systemview/fleetbtn.png", 4));
		
		return buttons;
	}

	
}
