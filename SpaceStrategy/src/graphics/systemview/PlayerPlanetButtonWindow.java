package graphics.systemview;

import graphics.FadeButton;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

import states.SystemView;

public class PlayerPlanetButtonWindow extends ButtonWindow {

	public PlayerPlanetButtonWindow(int x, int y) {
		super(x, y, PlayerPlanetButtonWindow.generateButtons(x, y));
	}
	
	
	
	
	
	
	
	
	private static ArrayList<FadeButton> generateButtons(int x, int y){
		ArrayList<FadeButton> buttons = new ArrayList<FadeButton>();
		buttons.add( new FadeButton(x, y, "res/buttons/systemview/informationbtn.png", buttons.size()));
		buttons.add( new FadeButton(x, y + SystemView.BUTTON_HEIGHT, "res/buttons/systemview/productionbtn.png", buttons.size()));
		buttons.add( new FadeButton(x, y + SystemView.BUTTON_HEIGHT * buttons.size(), "res/buttons/systemview/buildingsbtn.png", buttons.size()));
		buttons.add( new FadeButton(x, y + SystemView.BUTTON_HEIGHT * buttons.size(), "res/buttons/systemview/defensesbtn.png", buttons.size()));
		buttons.add( new FadeButton(x, y + SystemView.BUTTON_HEIGHT * buttons.size(), "res/buttons/systemview/fleetbtn.png", buttons.size()));
		
		return buttons;
	}

	
}
