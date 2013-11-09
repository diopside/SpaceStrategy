package graphics;

import java.util.ArrayList;
import java.util.Collection;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import states.SpaceStrategy;

public class ListWindow implements Clickable, Renderable {

	private Image header, footer;
	private int x, y, listStartX;
	private final int listLength = 8, WIDTH = 999, HEIGHT = 725;
	private ArrayList<Listable> listItems;
	private InfoBox[] boxes;
	
	
	//***************************************** Constructors and Initialization Methods **************************************
	public ListWindow(int x, int y){
		this.x = x;
		this.y = y;
		boxes = new InfoBox[8];
		listItems = new ArrayList<Listable>();
		initImages();
		for (int i = 0; i < boxes.length; i ++){
			boxes[i] = new InfoBox(x, y + header.getHeight() + InfoBox.HEIGHT * i);
		}
	}
	private void initImages(){
		
		try {
			header = new Image("res/buttons/systemview/centerheader.png");
			footer = new Image("res/buttons/systemview/centerfooter.png");
		} catch (SlickException exception) {
			exception.printStackTrace();
		}
	}
	
	
	//****************************************** General Methods ***********************************************************
	public void setList(ArrayList<Listable> items){
		listItems.addAll(items);
		listStartX = 0;
	}
	
	public void clearList(){
		listItems.clear();
	}
	
	public void renderBoxes(Graphics g){
		int index = (listStartX >= listItems.size()) ? 0 : listStartX;
		System.out.println(index + "    " + listItems.size());
		for (int totalRendered = 0; (totalRendered < listLength) && (totalRendered < listItems.size()); totalRendered ++){
			boxes[totalRendered].setInformation(listItems.get(index));
			boxes[totalRendered].render(g, 0, 0);
			index = (index + 1 >= listItems.size()) ? 0 : index + 1;
		}
		
		
	}
	
	public ArrayList<Listable> getListItems(){
		return listItems;
	}
	
	
	
	
	// ********************************** Interface Methods *****************************************************************
	@Override
	public void render(Graphics g, int xOffset, int yOffset) {
		header.draw(x, y);
		footer.draw(x, SpaceStrategy.HEIGHT - footer.getHeight());
		
		renderBoxes(g);
		
		
	}
	
	@Override
	public Shape getShape() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	@Override
	public boolean contains(int x, int y) {
		return getShape().contains(x, y);
	}
	
	
}
