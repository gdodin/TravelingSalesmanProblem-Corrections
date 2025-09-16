package main;

import input.CitiesManager;
import strategies.Soluce;
import strategies.StrategyManager;
import strategies.SoluceType;
import ui.DisplayUI;

public class Solver {
	
	private Soluce soluceToDisplay = null;
	private DisplayUI display;

	public static void main(String[] args) {
		
		Solver main = new Solver();
		main.prepare();	
		main.searchSoluce();
		main.displaySoluce();

	}

	private void displaySoluce() {
		display.setSoluce(soluceToDisplay);
		display.refresh();
	}

	private void searchSoluce() {
		soluceToDisplay = StrategyManager.createSolucePath(SoluceType.BAB);
	}

	private void prepare() {
		CitiesManager.getInstance().loadCitiesFromFile("data/Villes.csv");
		
		display = new DisplayUI();

	}

}
