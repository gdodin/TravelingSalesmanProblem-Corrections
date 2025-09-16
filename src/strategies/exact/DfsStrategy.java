package strategies.exact;

import java.util.ArrayList;
import java.util.List;

import business.CitiesPath;
import business.City;
import input.CitiesManager;
import strategies.PathStategy;
import ui.DisplayPanel;

public class DfsStrategy extends PathStategy {

	private CitiesPath soluce = null;
	private long nbCompleteSoluces = 0;
	
	@Override
	public CitiesPath runStrategy() {
		
		CitiesPath current = new CitiesPath();
		List<City> remainingCities = CitiesManager.getInstance().getCities();
		City startingCity = remainingCities.remove(0);
		current.addCity(startingCity);

		solveDFS(current);

		System.out.println("Nombre de parcours complets évalués : " + nbCompleteSoluces);
		
		return soluce;
	}

	private void solveDFS(CitiesPath current) {
		if (current.getRemainingCities().isEmpty()) {
			current.closeWithStartingCity();
			nbCompleteSoluces++;
			checkIfBetter(current);
		} else {
			for (City aCity : current.getRemainingCities()) {
				CitiesPath newCitiesPath = new CitiesPath(current.getCities());
				newCitiesPath.addCity(aCity);
		
				solveDFS(newCitiesPath);
			}
		}
	}

	private void checkIfBetter(CitiesPath current) {
		if (soluce == null || current.getLength() < soluce.getLength()) {
				soluce = current;
				DisplayPanel.instance.setPath2display(soluce);

		}
	}

}
