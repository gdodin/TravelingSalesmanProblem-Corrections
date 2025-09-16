package strategies;

import business.CitiesPath;

public class Soluce {

	private CitiesPath path;
	private double duration;
	
	public Soluce(CitiesPath path, double duration) {
		super();
		this.path = path;
		this.duration = duration;
	}

	public CitiesPath getPath() {
		return path;
	}

	public double getDuration() {
		return duration;
	}
	
	
	
}
