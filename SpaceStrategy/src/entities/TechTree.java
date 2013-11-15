package entities;

import java.util.ArrayList;

public class TechTree {

	
	
	
	
	
	
	public float getMaxProductionModifier(){
		return 1.5f;
	}
	
	
	
	
	public static void main(String[] args){
		ArrayList<String> a = new ArrayList<>();
		a.add("New");
		a.add("Cool");
		a.add("ExistsStill");
		

		ArrayList<String> b = new ArrayList<>();
		b.add("New");
		b.add("Cool");
		b.add("New");
		b.add("Cool");
		b.add("Byebye");
		
		a.removeAll(b);
		
		for (String s: a){
			System.out.println(a);
		}
	}
	
	
}
