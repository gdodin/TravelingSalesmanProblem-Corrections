package strategies.heuristics.genetic;

import java.util.Collections;

import business.CitiesPath;
import input.CitiesManager;

public class Individual implements Comparable<Individual> {

	private CitiesPath genome;
	
	public Individual() {
		super();
		genome = randomPath();
	}

	public Individual(CitiesPath parentGenome) {
		genome = new CitiesPath(parentGenome.getCities());
	}

	private CitiesPath randomPath() {
		CitiesPath orderedThenShuffled = new CitiesPath(CitiesManager.getInstance().getCities());
	    Collections.shuffle(orderedThenShuffled.getCities());
	    
	    return new CitiesPath(orderedThenShuffled.getCities());
	} 

	public CitiesPath getGenome() {
		return genome;
	}

	public double getLength() {
		return genome.getLength();
	}

	public int compareTo(Individual otherGenome) {
		if(getLength() > otherGenome.getLength())
            return 1;
        else if(getLength() < otherGenome.getLength())
            return -1;
        else
            return 0;
    }
}
