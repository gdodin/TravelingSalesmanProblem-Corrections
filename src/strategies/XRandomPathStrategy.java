package strategies;

import java.util.List;
import java.util.Random;

import business.CitiesPath;
import business.City;
import input.CitiesManager;

public class XRandomPathStrategy extends PathStategy {

	private static final int NB_ITERATIONS = 1000000;

	@Override
	public CitiesPath runStrategy() {

		CitiesPath bestSoluce = null; 
		
		for (int i = 0; i < NB_ITERATIONS; i++) {

			CitiesPath currentSoluce = getRandomSoluce();
			if (bestSoluce == null || currentSoluce.getLength() < bestSoluce.getLength()) {
				bestSoluce = currentSoluce;
			}
		}
		
		return bestSoluce;
	}

	private CitiesPath getRandomSoluce() {
		CitiesPath soluce = new CitiesPath();

		List<City> remainingCities = CitiesManager.getInstance().getCities();
		Random random = new Random();

		while (!remainingCities.isEmpty()) {
			int randomIndex = random.nextInt(remainingCities.size());
			City randomCity = remainingCities.remove(randomIndex);
			soluce.addCity(randomCity);
		}
		soluce.closeWithStartingCity();

		return soluce;
	}
}