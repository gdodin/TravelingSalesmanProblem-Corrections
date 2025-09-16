package strategies;

import java.util.List;

import business.CitiesPath;
import business.City;
import input.CitiesManager;

public class BestNnStrategy extends PathStategy {

	@Override
	public CitiesPath runStrategy() {

		CitiesPath bestSoluce = null; 
		
		for (City startCity : CitiesManager.getInstance().getCities()) {

			CitiesPath currentSoluce = getNNSoluceFrom(startCity);
			if (bestSoluce == null || currentSoluce.getLength() < bestSoluce.getLength()) {
				bestSoluce = currentSoluce;
			}
		}
		System.out.println("Meilleure ville depart => " + bestSoluce.getCityAt(0).getName());
		return bestSoluce;
	}

	private CitiesPath getNNSoluceFrom(City startCity) {
		CitiesPath soluce = new CitiesPath();
		List<City> remainingCities = CitiesManager.getInstance().getCities();
		
		City currentCity = startCity;
		soluce.addCity(currentCity);
		
		while(!remainingCities.isEmpty()) {
			 
			City closestCity = currentCity.findClosestCityFrom(remainingCities);
			remainingCities.remove(closestCity);
			soluce.addCity(closestCity);
			currentCity = closestCity;
		}
		soluce.closeWithStartingCity();
		
		return soluce;
	}
}
