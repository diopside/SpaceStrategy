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
	private int x, y, listStartIndex, selectedBox;
	private final int LIST_LENGTH = 8, WIDTH = 999, HEIGHT = 725;
	private ArrayList<Listable> listItems;
	private InfoBox[] boxes;
	
	
	//***************************************** Constructors and Initialization Methods **************************************
	public ListWindow(int x, int y){
		this.x = x;
		this.y = y;
		boxes = new InfoBox[LIST_LENGTH];
		listItems = new ArrayList<Listable>();
		initImages();
		for (int i = 0; i < LIST_LENGTH; i ++){
			boxes[i] = new InfoBox(x, y + header.getHeight() + i * InfoBox.HEIGHT);
		}
		header.setAlpha(.65f);
		footer.setAlpha(.65f);
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
		listStartIndex = 0;
	}
	
	
	public void clearList(){
		listItems.clear();
	}
	
	private void renderBoxes(Graphics g){
		for (int i = 0; (i < boxes.length) && (i < listItems.size()); i ++){
			boxes[i].render(g, 0, 0);
		}
		
		
		
	}
	
	public ArrayList<Listable> getListItems(){
		return listItems;
	}
	
	public int checkIfButtonPressed(int mouseX, int mouseY){
		/*
		 * This will return the index of the box that was selected, if no box was selected it will return a -1
		 */
		
		setBoxData();
		
		for (int i = 0; (i < LIST_LENGTH) && (i < listItems.size()); i ++)
			if (boxes[i].contains(mouseX, mouseY))
				return i;
		
		
		checkHeader(mouseX, mouseY);
		checkFooter(mouseX, mouseY);
		
		return -1;
	}
	
	public void checkHeader(int mouseX, int mouseY){
		Rectangle headerBox = new Rectangle(x, y, header.getWidth(), header.getHeight());
		if (headerBox.contains(mouseX, mouseY)){
			listStartIndex = (listStartIndex - 1 < 0)? listItems.size() - 1 : listStartIndex - 1;
			SpaceStrategy.click1.play();
		}
		
			// TO DO: edit this to check for sorts, implement comparators for the listables
	}
	
	public void checkFooter(int mouseX, int mouseY){
		Rectangle footerBox = new Rectangle(x, SpaceStrategy.HEIGHT - footer.getHeight(), footer.getWidth(), footer.getHeight());
		
		if (footerBox.contains(mouseX, mouseY)){
			listStartIndex = (listStartIndex + 1 >= listItems.size()) ? 0 : listStartIndex + 1;
			SpaceStrategy.click1.play();
		}
	}
	
	
	
	public Listable getListable(int index){
		return boxes[index].getListable();
	}
	
	public void selectBox(int index){
		
		/*
		 * If a box was selected all of the unselected boxes should be set semi-transparent and a clicking sound should play
		 */
		for (InfoBox box: boxes){
			box.renderTransparent(true);
		}
		boxes[index].renderTransparent(false);
		
		SpaceStrategy.click1.play();
		
	}
	
	public void setBoxData(){
		for (int i = 0; (i < LIST_LENGTH) && (i < listItems.size()); i ++){
			boxes[i].setInformation(listItems.get((i + listStartIndex) % listItems.size()));
		}
		
	}
	
	
	
	// ********************************** Interface Methods *****************************************************************
	@Override
	public void render(Graphics g, int xOffset, int yOffset) {
		header.draw(x, y);
		footer.draw(x, SpaceStrategy.HEIGHT - footer.getHeight());
		
		setBoxData();
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
