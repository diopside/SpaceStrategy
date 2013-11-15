package graphics.listwindows;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import states.SpaceStrategy;
import entities.Constructable;
import entities.Planet;
import graphics.Listable;
import entities.buildings.*;
import entities.ships.*;

public class QueueListWindow extends ListWindow {

	ArrayList<Constructable> queue;
	Planet p;
	

	public QueueListWindow(int x, int y, ArrayList<Constructable> constructables, ArrayList<Constructable> queue, Planet p) {
		super(x, y);
		this.queue = queue;
		listItems = constructables;
		this.p = p;
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
		Constructable c = (Constructable) listItems.get((index + listStartIndex) % listItems.size());
		if (c instanceof House)
			queue.add(new House(p.getPlanetBuildingIDMax(),p));
		else if (c instanceof Factory)
			queue.add(new Factory(p.getPlanetBuildingIDMax(), p));
		else if (c instanceof ColonyShip)
			queue.add(new ColonyShip(0,null,null,null,null));
		
		SpaceStrategy.click1.play();
	}

	@Override
	void setBoxData() {
		for (int i = 0; (i < LIST_LENGTH) && (i < listItems.size()); i ++){
                        boxes[i].setInformation((Listable) listItems.get((i + listStartIndex) % listItems.size()));
                }
	}

}
