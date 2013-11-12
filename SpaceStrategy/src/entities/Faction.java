package entities;

import java.util.ArrayList;

import org.newdawn.slick.Color;

import entities.buildings.Factory;
import entities.buildings.House;

public class Faction {

	//*****************Final and Static members*************************
	private final Color COLOR;
	private final int ID;
	
	//****************Ordinary Members*********************************
	private String name;
	private int wealth, minerals, food;
	private float population, populationLoyalty;
	private ArrayList<Planet> planets;
	private boolean[] exploredSystems; // something like this will probably have to be implemented so the played can't view full details of their opponent
	private Game world;
	private TechTree techTree;
	
	
	//**********************Constructors and initialization methods *****************
	
	public Faction(int color, int ID, String name, Game world){
		COLOR = new Color(color);
		this.ID = ID;
		planets = new ArrayList<Planet>();
		this.name = name;
		exploredSystems = new boolean[world.getSystems().length];
	}

	
	//************************Getters and Setters********************************
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWealth() {
		return this.wealth;
	}
	public void setWealth(int wealth) {
		this.wealth = wealth;
	}
	public int getMinerals() {
		return this.minerals;
	}
	public void setMinerals(int minerals) {
		this.minerals = minerals;
	}
	public int getFood() {
		return this.food;
	}
	public void setFood(int food) {
		this.food = food;
	}
	public float getPopulation() {
		return this.population;
	}
	public void setPopulation(float population) {
		this.population = population;
	}
	public float getPopulationLoyalty() {
		return this.populationLoyalty;
	}
	public void setPopulationLoyalty(float populationLoyalty) {
		this.populationLoyalty = populationLoyalty;
	}
	public Color getCOLOR() {
		return this.COLOR;
	}
	public int getID() {
		return this.ID;
	}
	public ArrayList<Planet> getPlanets(){
		return planets;
	}
	

	public TechTree getTechTree() {
		return techTree;
	}
	
	
	//*********************************** General Methods *****************************************
	public boolean equals(Faction otherFaction){
		return (ID == otherFaction.getID());
	}
	
	public String toString(){
		return this.name;
	}
	
	public void init(StarSystem s){
		Planet p = s.getPlanets()[(int) (Math.random() * s.getPlanets().length)];
		addPlanet(p);
		p.addBuilding(new House(p.getPlanetBuildingIDMax(),p));
		p.addBuilding(new Factory(p.getPlanetBuildingIDMax(), p));
		p.addBuilding(new House(p.getPlanetBuildingIDMax(),p));
		p.addBuilding(new Factory(p.getPlanetBuildingIDMax(), p));
		p.setPopulation(2.0f);
	}
	
	public void addPlanet(Planet p){
		planets.add(p);
		p.setOwner(this);
	}
	
	public void resolveTurn(){
		for (Planet p: planets){
			p.resolveTurn();
		}
	}


	
	
	
}
























