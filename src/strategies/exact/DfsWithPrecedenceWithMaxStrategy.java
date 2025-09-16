package strategies.exact;

import java.util.List;

import business.CitiesPath;
import business.City;
import input.CitiesManager;
import strategies.PathStategy;
import ui.DisplayPanel;

public class DfsWithPrecedenceWithMaxStrategy extends PathStategy {

	private CitiesPath soluce = null;
	private long nbCompleteSoluces = 0;
	private City cityA;
	private City cityB;
	private int cptMaxCuts = 0;

	@Override
	public CitiesPath runStrategy() {

		CitiesPath current = new CitiesPath();
		List<City> remainingCities = CitiesManager.getInstance().getCities();

		cityA = remainingCities.get(1);
		cityB = remainingCities.get(2);

		City startingCity = remainingCities.remove(0);
		current.addCity(startingCity);

		solveDFS(current);

		System.out.println("Nombre de parcours complets évalués : " + nbCompleteSoluces);
		System.out.println("Nombre d'utilisation de la coupure borne max : " + cptMaxCuts);

		return soluce;
	}

	private void solveDFS(CitiesPath current) {
		if (current.getRemainingCities().isEmpty()) {
			current.closeWithStartingCity();
			nbCompleteSoluces++;
			checkIfBetter(current);
		} else {
			if (!cutMaxPossible(current.getLength())) {
				for (City aCity : current.getRemainingCities()) {
					if (aCity.equals(cityB) && !current.goesBy(cityA)) {
						continue;
					}
					CitiesPath newCitiesPath = new CitiesPath(current.getCities());
					newCitiesPath.addCity(aCity);

					solveDFS(newCitiesPath);
				}
			} else {
				cptMaxCuts++;
			}
		}
	}

	private boolean cutMaxPossible(double length) {
		if (soluce == null)
			return false;
		if (soluce.getLength() > length)
			return false;
		return true;
	}

	private void checkIfBetter(CitiesPath current) {
		if (soluce == null || current.getLength() < soluce.getLength()) {
			soluce = current;
			DisplayPanel.instance.setPath2display(soluce);

		}
	}

}
