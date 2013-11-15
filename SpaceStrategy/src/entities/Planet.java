package entities;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import entities.buildings.Building;
import entities.specials.Special;
import states.SpaceStrategy;
import tools.Round;
import graphics.Clickable;
import graphics.Renderable;

public class Planet implements Renderable, Clickable {

	private final int ID, MAX_BUILDINGS;
	private short planetBuildingIDMax; // each building will be given an ID which will be 1 higher than the ID of the last building created
	private String name;
	
	private int proximityToSun, size, imageIndex, energy, production, pollution, maximumPopulation, productionPoints, previousProductionPoints;
	/*ProxToSun represents the index in the starSystemArray, energy will dictate potential buildings and other energy consuming things, will be provided by buildings and other things
	 * production will be productive capacity of factories and the like yielding productionPoints each turn
	 */
	
	
	
	private boolean drawTransparent; // if one planet is selected the rest will be slightly grayed out
	private float population, mineralRating, bioRating, hospitableRating, loyalty;
	private Faction owner;
	private ArrayList<Constructable> queue;
	private ArrayList<Building> buildings;
	private ArrayList<Special> specials;
	
	
	//*********************** Static and final members***********************
	public static Image[] PLANET_IMAGES;
	public static final int PLANET_RENDER_SPACING_X = 80, PLANET_RENDER_SIZE = 100, PLANET_RENDER_Y = 30;
	
	
	
	//**********************Constructors and Initialization methods********************************
	
	
	
	public Planet(String systemName, int size, int proximityToSun, int systemID){
		name = systemName + "-" + (proximityToSun + 1); // this will create the default name for a planet
		this.size = size;
		this.proximityToSun = proximityToSun;
		buildings = new ArrayList<Building>();
		imageIndex = (int) (Math.random() * PLANET_IMAGES.length);
		Planet.generateRandomStats(this);
		roundValues();
		ID = (systemID * 10) + proximityToSun;
		queue = new ArrayList<Constructable>();
		
		maximumPopulation = 3 + size;
		MAX_BUILDINGS = 5 * size;
	}
	

	
	//********************************************* Getters and Setters ****************************************************
	public int getID(){
		return ID;
	}
	public int getSystemID(){
		return ID/10;
	}
	public Faction getOwner(){
		return owner;
	}
	public void setOwner(Faction f){
		owner = f;
	}
	public int getProximityToSun(){
		return proximityToSun;
	}
	public String getName(){
		return name;
	}
	public float getPopulation(){
		return population;
	}
	public int getSize() {
		return this.size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getEnergy() {
		return this.energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	public int getProduction() {
		return this.production;
	}
	public void setProduction(int production) {
		this.production = production;
	}
	public int getPollution() {
		return this.pollution;
	}
	public void setPollution(int pollution) {
		this.pollution = pollution;
	}
	public float getMineralRating() {
		return this.mineralRating;
	}
	public void setMineralRating(float mineralRating) {
		this.mineralRating = mineralRating;
	}
	public float getBioRating() {
		return this.bioRating;
	}
	public void setBioRating(float bioRating) {
		this.bioRating = bioRating;
	}
	public float getHospitableRating() {
		return this.hospitableRating;
	}
	public void setHospitableRating(float hospitableRating) {
		this.hospitableRating = hospitableRating;
	}
	public float getLoyalty() {
		return this.loyalty;
	}
	public void setLoyalty(float loyalty) {
		this.loyalty = loyalty;
	}
	public void setPopulation(float population) {
		this.population = population;
	}
	public void setDrawTransparent(boolean b){
		drawTransparent = b;
	}
	public short getPlanetBuildingIDMax(){
		short s = planetBuildingIDMax;
		planetBuildingIDMax ++;
		return s;
	}
	
	public ArrayList<Building> getBuildings(){
		return buildings;
	}
	
	public void setMaximumPopulation(int a){
		maximumPopulation = a;
	}
	public int getMaximumPopulation(){
		return maximumPopulation;
	}
	
	public ArrayList<Constructable> getQueue(){
		return queue;
	}
	
	public int getProductionPoints(){
		return productionPoints;
	}
	
	public void setProductionPoints(int a){
		productionPoints = a;
	}
	//********************General methods****************************************************************************
	public void roundValues(){
		bioRating = (float) Round.round(bioRating, 4);

		mineralRating = (float) Round.round(mineralRating, 4);

		population = (float) Round.round(population, 4);

		hospitableRating = (float) Round.round(hospitableRating, 4);
	}
	
	public void addBuilding(Building b){
		buildings.add(b);
		b.addBuilding(this);
	}
	
	private float calculatePopulationGrowth(){
		// this formula will be updated to account for food totals, planetary, technology, and species factors
		return (population * .03f);
	}
	
	public void resolveTurn(){
		grow();
		updateResources();
		if (queue.size() > 0)
			constructItems(queue.size() - 1);
		deconstructItems();
		
		
		previousProductionPoints = productionPoints;
		productionPoints = 0; // Production can not be saved, but the last turns production will be displayed
	}
	
	private void deconstructItems(){
		ArrayList<Removeable> removeables = new ArrayList<>();
		for (int i = 0; i < queue.size(); i ++){
			if (queue.get(i).isBeingDeconstructed()){
				removeables.add(queue.get(i));
			}
		}
		
		queue.removeAll(removeables);
		
		
		
	}
	
	private void constructItems(int index){
		ArrayList<Building> buildingsBuilt = new ArrayList<>();
		
		for (Constructable c: queue){
			c.construct(this);
			if (c.getCompletionPercentage() == 1f){
				if (c instanceof Building)
					buildingsBuilt.add((Building) c);
			}
		}
		
		for (Building b: buildingsBuilt)
			addBuilding(b);
		
		queue.removeAll(buildingsBuilt);
		
		
		
	}
	
	
	
	private float getLaborEfficiency(){
		float laborNeed = 0;
		for (Building b: buildings){
			laborNeed += b.getLaborUsage();
		}
		
		if(laborNeed == 0)
			return Float.MAX_VALUE;
		return (laborNeed/production);
		
	}
	
	private void updateResources(){
		/*
		 * This method will determine and set the amount of production, credits, science, food, among other things the planet will be producing.
		 */
		
		float productionMod = (getLaborEfficiency() >= owner.getTechTree().getMaxProductionModifier()) ? owner.getTechTree().getMaxProductionModifier() : getLaborEfficiency();
		productionPoints = (int) (productionMod * production);
		System.out.println("PP: " + productionPoints);
		
		
		
	}
	
	private void grow(){
		float addedPop = calculatePopulationGrowth();
		population = (addedPop + population >= maximumPopulation) ? maximumPopulation : addedPop + population;
	}
	
	
	
	
	//***********************************************************Interface Methods*******************************************
	@Override
	public boolean contains(int x, int y) {
		return getShape().contains(x, y);
	}

	@Override
	public void render(Graphics g, int xOffset, int yOffset) {
		Image planetImage = PLANET_IMAGES[imageIndex].copy(); // since there might be 2-3 hundred planets its better to just store the 19 planet images statically and then reference them
		if (drawTransparent){// in system view all planets but the selected planet will be drawn semi-transparent
			planetImage.setAlpha(.25f);
		}
		planetImage.draw((PLANET_RENDER_SIZE + PLANET_RENDER_SPACING_X)*proximityToSun, PLANET_RENDER_Y);
		
		
		if (owner != null) // the way to visually indicate planet ownership is drawing the name in the faction color, this will accomplish that task
			g.setColor(owner.getCOLOR());
		else
			g.setColor(Color.white);
		
		
		g.setFont(SpaceStrategy.NEUROPOL); // spacey font
		g.drawString(this.name,(float) ((PLANET_RENDER_SIZE + PLANET_RENDER_SPACING_X)* proximityToSun + .25*PLANET_RENDER_SIZE), PLANET_RENDER_Y + PLANET_RENDER_SIZE + 10);
	}

	@Override
	public Shape getShape() {
		return new Rectangle(proximityToSun * (PLANET_RENDER_SIZE + PLANET_RENDER_SPACING_X), PLANET_RENDER_Y, PLANET_RENDER_SIZE, PLANET_RENDER_SIZE);
	}

	//*********************************************************Static class methods******************************************
	public static void initPlanetImages(){
		PLANET_IMAGES = new Image[19];
		for (int i = 0; i < PLANET_IMAGES.length; i ++){
			try {
				PLANET_IMAGES[i] = new Image("res/planets/planet"+(i+2)+".png").getScaledCopy(PLANET_RENDER_SIZE, PLANET_RENDER_SIZE);
			} catch (SlickException exception) {
				exception.printStackTrace();
			}
		}
	}
	
	private static void generateRandomStats(Planet p){
		p.setBioRating((float) (Math.random() * 10));
		p.setMineralRating((float) (Math.random() * 10));
		p.setHospitableRating((float) (Math.random() * 10));
		
	}
	
} // end class






