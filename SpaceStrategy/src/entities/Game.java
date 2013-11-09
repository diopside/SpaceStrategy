package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import org.newdawn.slick.state.StateBasedGame;

public class Game {

	private int turn;
	private ArrayList<Faction> factions;
	private StarSystem[] systems;
	
	
	
	//************************************* Constructors and initialization methods ****************************************
	public Game(int numFactions){
		turn = 0;
		factions = new ArrayList<Faction>();
		systems = new StarSystem[((int)(Math.random() * 16 + 16))];
		generateRandomSystems(systems);
		
		factions.add(new Faction(0x00FFFF, 0, "player", this));
		factions.add(new Faction(0xFFD800, 1, "enemy1", this));
		factions.add(new Faction(0x4CFF00, 2, "enemy2", this));
		
		for (int i = 0; i < factions.size(); i ++){
			factions.get(i).init(systems[i]);
		}
		
	}
	
	
	//********************************************* Getters and Setters **********************************************************************
	
	public int turn(){
		return turn;
	}
	
	public void incTurn(){
		turn ++;
	}
	
	public ArrayList<Faction> getFactions(){
		return factions;
	}
	
	public StarSystem[] getSystems(){
		return systems;
	}
	
	//*************************************** General members **********************************************************
	
	public void endTurn(StateBasedGame game){
		
		
		
	}
	
	
	
	
	
	
	
	
	
	//*********************Static class members****************************************************************
	
	private static void generateRandomSystems(StarSystem[] systems){
		String[] starNames = new String[] {
				"Andromeda", "Aquarius","Cancer","Capricorn","Pisces","Virgo","Scorpio","Aries","Bootes","Centarus","Crux","Dorado", "Gemini","Hydrus",
				"Libra","Leo","Norma","Musca","Orion","Pegasus","Pavo","Perseus","Vela","Ursa","Taurus","Indus","Vega","Alpha","Beta","Lambda","Gamma","Delta","Epsilon","Zeta","Eta",
				"Theta","Iota","Kappa","Mu","Nu","Xi","Omicron","Pi","Rho","Sigma","Tau","Upsilon","Phi","Chi","Si","Omega","Gauss","Euclid","Euler","Leibniz","Fermat","Riemann","Cantor"};
		Stack<String> names = new Stack<String>();
		int[] lx = new int[20];
		int[] ly = new int[16];

		
		for (String s: starNames)
			names.add(s);
		
		Collections.shuffle(names);
		
		for (int i = 0; i < systems.length; i ++){
			int ix = 0, iy = 0;
			boolean uniqueLoc = false;
			
			while (!uniqueLoc){ // get a unique location for the star systems
				ix = (int) (Math.random() * 20);
				iy = (int) (Math.random() * 16);
				if (lx[ix] == 0 || ly[iy] == 0){
					uniqueLoc = true;
					lx[ix] = 1;
					ly[iy] = 1;
				}
			}
			
			int numPlanets = (int) (Math.random() * 8 + 1);
			systems[i] = new StarSystem(ix * 100, iy * 100, numPlanets, names.pop(), i);
		}
	}
	
	
}
