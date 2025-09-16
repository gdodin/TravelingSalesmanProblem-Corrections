package strategies;

import java.util.List;
import java.util.Random;

import business.CitiesPath;
import business.City;
import input.CitiesManager;

public class NNPathStrategy extends PathStategy {

	@Override
	public CitiesPath runStrategy() {
		
		CitiesPath soluce = new CitiesPath();
		List<City> remainingCities = CitiesManager.getInstance().getCities();
		
		City currentCity = remainingCities.remove(remainingCities.size()-1);
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