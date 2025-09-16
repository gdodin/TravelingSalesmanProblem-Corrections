package strategies.heuristics.basic;

import business.CitiesPath;
import business.City;
import input.CitiesManager;
import strategies.PathStategy;

public class DefaultPathStrategy extends PathStategy {

	@Override
	public CitiesPath runStrategy() {
		
		CitiesPath soluce = new CitiesPath();
		
		for (City aCity: CitiesManager.getInstance().getCities()) {
			soluce.addCity(aCity);
		}
		soluce.closeWithStartingCity();
		
		return soluce;
	}
}
