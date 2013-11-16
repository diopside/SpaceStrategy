package graphics.listwindows;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import states.SpaceStrategy;
import entities.ships.Ship;
import graphics.Listable;

public class ShipSelectionListWindow extends ListWindow {

	boolean[] selections;
	
	public ShipSelectionListWindow(int x, int y, ArrayList<Ship> ships, boolean[] selections) {
		super(x, y);
		this.selections = selections;
		listItems = (ArrayList<Ship>)ships.clone();
	}

	@Override
	public void render(Graphics g, int xOffset, int yOffset) {
		header.draw(x, y);
                footer.draw(x, SpaceStrategy.HEIGHT - footer.getHeight());
                
                setBoxData();
                renderBoxes(g);
	}

	@Override
	public void selectBox(int index) {
		selections[(index + listStartIndex) % listItems.size()] = !selections[(index + listStartIndex) % listItems.size()];
		SpaceStrategy.click1.play();
		setBoxData();
		
	}

	@Override
	void setBoxData() {
		for (int i = 0; (i < LIST_LENGTH) && (i < listItems.size()); i ++){
			boxes[i].setInformation((Listable) listItems.get((i + listStartIndex) % listItems.size()));
			boxes[i].renderTransparent(selections[(i + listStartIndex) % listItems.size()]);
		}
	}

}
