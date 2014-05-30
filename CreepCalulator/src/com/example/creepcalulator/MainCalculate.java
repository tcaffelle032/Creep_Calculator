package com.example.creepcalulator;

import java.util.Random;
public class MainCalculate {	
	
	
	public int GoldRecieved;
	public int CreepsKilled;
	
	public int calculateGoldEarned(int target){
		Random creepKilled = new Random();
		//Reset counters
		GoldRecieved = 0;
		CreepsKilled = 0;
		
		while(GoldRecieved <= target){
			//While loop that does the calculating 
			
			int gold = creepKilled.nextInt(48-38)+38; //makes sure the the value has at least 38 gold
			GoldRecieved += gold;
			CreepsKilled ++;
		}
		return CreepsKilled;
	}

}
