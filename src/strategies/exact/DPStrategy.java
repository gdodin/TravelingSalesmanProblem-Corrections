package strategies.exact;

import java.util.ArrayList;
import java.util.List;

import business.CitiesPath;
import business.City;
import input.CitiesManager;
import strategies.PathStategy;

public class DPStrategy extends PathStategy {

    private City startCity;
    private Memoization memo = new Memoization();

    @Override
    public CitiesPath runStrategy() {

        startCity = CitiesManager.getInstance().getCity(0);

        List<City> cities2Visit = new ArrayList<City>(CitiesManager.getInstance().getCities());
        cities2Visit.remove(startCity);

        double cost = dpSearch(startCity, cities2Visit);

        System.out.println("memo used: " + memo.getMemoUsed() + " times");
        System.out.println(cost);
        return rebuildPath(cost);
    }

    private CitiesPath rebuildPath(double cost) {
        CitiesPath res = new CitiesPath();
        res.addCity(startCity);

        double currentCost = cost;
        List<City> cities2Visit = new ArrayList<City>(CitiesManager.getInstance().getCities());
        cities2Visit.remove(startCity);

        City currentCity = startCity;

        while (!cities2Visit.isEmpty()) {
            for (City nextCity : cities2Visit) {
                List<City> remainCities = new ArrayList<City>(cities2Visit);
                remainCities.remove(nextCity);

                Double minCost = memo.getValueFor(nextCity, remainCities);
                if (minCost == null)
                    minCost = CitiesManager.getInstance().distanceBetween(startCity,  nextCity);
                double distance = CitiesManager.getInstance().distanceBetween(currentCity, nextCity);
                double sum = distance + minCost;

//				if (remainCities.isEmpty()) {
//					distance += CitiesManager.getInstance().distanceBetween(nextCity, startCity);
//				}

                if (sum == currentCost) {
                    cities2Visit.remove(nextCity);
                    currentCost -= distance;
                    res.addCity(nextCity);
                    currentCity = nextCity;
                    break;
                }
            }
        }
        res.closeWithStartingCity();
        return res;
    }

    private double dpSearch(City currentCity, List<City> cities2Visit) {

        if (memo.getValueFor(currentCity, cities2Visit) != null) {
            return memo.getValueFor(currentCity, cities2Visit);
        }

        if (cities2Visit.isEmpty()) {
            return CitiesManager.getInstance().distanceBetween(currentCity, startCity);
        }

        double minDistance = Double.MAX_VALUE;

        for (City nextCity : cities2Visit) {
            List<City> remainCities = new ArrayList<City>(cities2Visit);
            remainCities.remove(nextCity);
            double newDistance = CitiesManager.getInstance().distanceBetween(currentCity, nextCity)
                    + dpSearch(nextCity, remainCities);
            if (newDistance < minDistance) {
                minDistance = newDistance;
            }
        }
        memo.saveValueFor(currentCity, cities2Visit, minDistance);

        return minDistance;
    }



}