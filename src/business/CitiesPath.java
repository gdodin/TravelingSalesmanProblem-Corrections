package business;

import java.util.ArrayList;
import java.util.List;

import input.CitiesManager;

public class CitiesPath {
	
	private List<City> cities;
	private double length;
	private List<City> remainingCities;
	
	private City latestCity;

	public CitiesPath() {
		super();
		cities = new ArrayList<>();
		remainingCities = CitiesManager.getInstance().getCities();
		length = 0.;
		latestCity = null;
	}

	public CitiesPath(List<City> cities) {
		this();
		for (City city: cities) {
			addCity(city);
		}
		//addCity(cities.get(0));
	}
	
	public City getCityAt(int index) {
		return cities.get(index);
	}

	public void addCity(City city) {
		cities.add(city);
		remainingCities.remove(city);
		
		if (latestCity != null) {
			length += CitiesManager.getInstance().distanceBetween(latestCity, city);
		}
		
		latestCity = city;
	}

	public double getLength() {
		return length;
	}

	public List<City> getCities() {
		return cities;
	}
	
	public int getNbCities() {
		return cities.size();
	}

	public void closeWithStartingCity() {
		addCity(cities.get(0));
		
	}

	public List<City> getRemainingCities() {
		return remainingCities;
	}

	public boolean goesBy(City city) {
		return cities.contains(city);
	}


    public boolean goesByAllCities() {
        return remainingCities.isEmpty();
    }
}
