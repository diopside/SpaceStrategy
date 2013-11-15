package graphics.listwindows;

import graphics.InfoBox;
import graphics.Listable;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import states.SpaceStrategy;

public class ViewListWindow extends ListWindow {
	

	public ViewListWindow(int x, int y, ArrayList<Listable> listables) {
		super(x, y);
		
		listItems = listables;
	}

	@Override
	public void selectBox(int index) {
		   /*
                 * If a box was selected all of the unselected boxes should be set semi-transparent and a clicking sound should play
                 */
                for (InfoBox box: boxes){
                        box.renderTransparent(true);
                }
                boxes[index].renderTransparent(false);
                
                SpaceStrategy.click1.play();
	}

	@Override
	public void render(Graphics g, int xOffset, int yOffset) {
			header.draw(x, y);
	                footer.draw(x, SpaceStrategy.HEIGHT - footer.getHeight());
	                
	                setBoxData();
	                renderBoxes(g);
	}

	@Override
	void setBoxData() {
		
		 for (int i = 0; (i < LIST_LENGTH) && (i < listItems.size()); i ++){
	                        boxes[i].setInformation((Listable) listItems.get((i + listStartIndex) % listItems.size()));
	                }
	}
	
	
	
	
	

}
