package project_sc.turtlez;

public class GeneticAlgorithmSearch {
	private Population population;
		
	public GeneticAlgorithmSearch(Population population) {
		this.population = population;
	}

	public Population getPopulation() {
		return population;
	}

	public void setPopulation(Population population) {
		this.population = population;
	}
	
	public Individu doSearch() {
		Individu result = new Individu(5);
		int stuck = 0;
		while (stuck < 10) {
			int first = this.population.idxFirst();
			int first2 = this.population.idxSecondFirst();
			this.population.doCrossover(this.population.get(first), this.population.get(first2));
			
			int newFirst = this.population.idxFirst();
			if(first == newFirst) {
				stuck++;
			}
			else {
				stuck = 0;
			}
			result.setAlels(this.population.get(newFirst).getAlels());
		}
		
		
		return result;
	}

}
