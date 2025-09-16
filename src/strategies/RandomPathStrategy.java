package strategies;

import java.util.List;
import java.util.Random;

import business.CitiesPath;
import business.City;
import input.CitiesManager;

public class RandomPathStrategy extends PathStategy {

	@Override
	public CitiesPath runStrategy() {
		
		CitiesPath soluce = new CitiesPath();
		
		List<City> remainingCities = CitiesManager.getInstance().getCities();
		Random random = new Random();
		
		while(!remainingCities.isEmpty()) {
			int randomIndex = random.nextInt(remainingCities.size()); 
			City randomCity = remainingCities.remove(randomIndex);
			soluce.addCity(randomCity);
		}
		soluce.closeWithStartingCity();
		
		return soluce;
	}
}