package graphics.listwindows;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import states.SpaceStrategy;
import entities.Activatable;
import entities.Removeable;
import graphics.Listable;

public class ActivityListWindow extends ListWindow{
	
	ArrayList<Activatable> activatables;

	public ActivityListWindow(int x, int y, ArrayList<Activatable> activatables) {
		super(x, y);
		listItems = activatables;
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
			boolean isActive = ((Activatable) listItems.get(index)).isActive();
			((Activatable) listItems.get(index)).setActivity(!isActive);
		
	}

	@Override
	void setBoxData() {
		
		for (int i = 0; (i < LIST_LENGTH) && (i < listItems.size()); i ++){
			boxes[i].setInformation((Listable) listItems.get((i + listStartIndex) % listItems.size()));
			// Set it to the opposite of the activity because if it is active it would trigger it to renderTransparent instead of the opposite
			boxes[i].renderTransparent(!((Activatable) listItems.get((i + listStartIndex) % listItems.size())).isActive());
		}
	}

}
