package strategies.exact;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import business.CitiesPath;
import business.City;
import input.CitiesManager;
import strategies.PathStategy;
import ui.DisplayPanel;

public class BABPathStrategy extends PathStategy {

    private CitiesPath best = null;
    private int cptCompletePaths;
    private int cptMaxCuts;
    private int cptMinCuts;

    private City cityA;
    private City cityB;

    @Override
    public CitiesPath runStrategy() {
        cptCompletePaths = 0;
        cptMaxCuts = 0;
        cptMinCuts = 0;
        List<City> cities = CitiesManager.getInstance().getCities();
        City startingCity = cities.remove(0);
        CitiesPath currentPath = new CitiesPath();
        currentPath.addCity(startingCity);

        cityA = cities.get(1);
        cityB = cities.get(2);

        babPath(currentPath);

        System.out.println("Nombre de solution complête : " + cptCompletePaths);
        System.out.println("Nombre de coupure par borne max : " + cptMaxCuts);
        System.out.println("Nombre de coupure par borne min : " + cptMinCuts);

        return best;
    }

    private void babPath(CitiesPath currentPath) {
        if (currentPath.goesByAllCities()) {
            currentPath.closeWithStartingCity();
            cptCompletePaths++;
            verifyBestPath(currentPath);
        } else {
            if(cutMax(currentPath)) {
                cptMaxCuts++;
                return;
            }
            if(cutMin(currentPath)) {
                cptMinCuts++;
                return;
            }
            for (City city : currentPath.getRemainingCities()) {

                if(city.equals(cityB) && !currentPath.goesBy(cityA)) {
                    continue;
                }

                CitiesPath newPath = new CitiesPath(currentPath.getCities());
                newPath.addCity(city);

                babPath(newPath);
            }
        }
    }

    private boolean cutMin(CitiesPath currentPath) {
        if(best == null) {
            return false;
        }
        double bestRemainingEdeges = calculateBestRemainingEdges(currentPath);
        double sum = currentPath.getLength() + bestRemainingEdeges;
        return sum >= best.getLength();
    }

    private double calculateBestRemainingEdges(CitiesPath currentPath) {
        double res = 0;
        List<Double> allEdges = CitiesManager.getInstance().getAllDistance();
        Collections.sort(allEdges);
        List<Double> remainingEdges = removeUsedEdges(currentPath, allEdges);
        int nbEdgeToTake = nbOfRemainingEdge(currentPath);
        res = sumNbFrom(nbEdgeToTake, remainingEdges);
        return res;
    }

    private List<Double> removeUsedEdges(CitiesPath currentPath, List<Double> edges) {
        City previousCity =  null;
        for (City currentCity: currentPath.getCities()) {
            if(previousCity != null) {
                Double distance = CitiesManager.getInstance().distanceBetween(previousCity, currentCity);
                edges.remove(distance);
            }
            previousCity = currentCity;
        }
        return edges;
    }

    private double sumNbFrom(int nbEdgeToTake, List<Double> remainingEdges) {
        double res = 0;
        for (int i = 0; i < nbEdgeToTake; i++) {
            res += remainingEdges.get(i);
        }
        return res;
    }

    private int nbOfRemainingEdge(CitiesPath currentPath) {
        return currentPath.getRemainingCities().size() + 1;
    }

    private boolean cutMax(CitiesPath currentPath) {
        return best!=null && currentPath.getLength() >= best.getLength();
    }

    private void verifyBestPath(CitiesPath currentPath) {
        if (best == null || currentPath.getLength() < best.getLength()) {
            best = currentPath;
            DisplayPanel.instance.setPath2display(currentPath);
        }

    }
}