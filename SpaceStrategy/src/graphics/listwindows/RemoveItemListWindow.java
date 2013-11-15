package graphics.listwindows;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import entities.Removeable;
import graphics.Listable;
import states.SpaceStrategy;

public class RemoveItemListWindow extends ListWindow{
	

	public RemoveItemListWindow(int x, int y, ArrayList<Removeable> removeables) {
		super(x, y);
		listItems = removeables;
		
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
		boolean status = ((Removeable) listItems.get(index)).isBeingDeconstructed();
		 ((Removeable) listItems.get(index)).setDeconstructionStatus(!status);
	}

	@Override
	void setBoxData() {
		for (int i = 0; (i < LIST_LENGTH) && (i < listItems.size()); i ++){
                        boxes[i].setInformation((Listable) listItems.get((i + listStartIndex) % listItems.size()));
                        boxes[i].renderTransparent(((Removeable) listItems.get((i + listStartIndex)%listItems.size())).isBeingDeconstructed());
                }
	}

}
