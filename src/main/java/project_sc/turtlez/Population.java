package project_sc.turtlez;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;
import java.util.TreeMap;

public class Population {
	private Individu[] population;
	private Domain domain;
	private int size;
	private Stack<Integer> number;
	private User user;
	private ArrayList<User> leftover;
	
	public Population(Domain domain, User user) {
		this.domain = domain;
		this.user = user;
		if(this.domain.size() > 9) {
			this.size = (int) (domain.size()/5);
			this.population = new Individu[this.size];
			this.number = generateRN(domain.size());
			this.leftover = new ArrayList<User>();
			generate(this.domain);
		}
	}
	
	public Individu[] getPopulation() {
		return population;
	}
	
	public boolean addLetfover(User user) {
		return this.leftover.add(user);
	}
	
	public ArrayList<User> getLeftover() {
		return this.leftover;
	}

	public void setPopulation(Individu[] population) {
		this.population = population;
	}
	
	public void replace(int index, Individu young) {
		this.population[index] = young;
	}

	public Domain getDomain() {
		return domain;
	}
	
	public Individu get(int index) {
		return this.population[index];
	}
	
	public int size() {
		return this.size;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}
	
	public Individu smallDomainSolution(List<User> list) {
		ValueComparator comp = new ValueComparator(this.user);
		
		Collections.sort(list, comp);
		User[] result = new User[5];
		for(int i = 0; i < result.length; i++) {
			result[i] = list.get(i);
		}
		Individu res = new Individu(result);
		return res;
	}
	
	public int idxFirst() {
		int first = 0;
		for(int i = 0; i < this.size; i++) {
			if(this.population[i].getFitness() >= this.population[first].getFitness()) {
				first = i;
			}
		}
		return first;
	}
	
	public int idxSecondFirst() {
		int first2 = 0;
		for(int i = 0; i < this.size; i++) {
			if((this.population[i].getFitness() >= 
					this.population[first2].getFitness()) && 
					(i != idxFirst())) {
				first2 = i;
			}
		}
		return first2;
	}
	
	public int idxLast() {
		int last = 0;
		for(int i = 0; i < this.size; i++) {
			if(this.population[i].getFitness() <= this.population[last].getFitness()) {
				last = i;
			}
		}
		return last;
	}
	
	public int idxSecondLast() {
		int last2 = 0;
		for(int i = 0; i < this.size; i++) {
			if((this.population[i].getFitness() <= 
					this.population[last2].getFitness()) && 
					(i != idxLast())) {
				last2 = i;
			}
		}
		return last2;
	}
	
	private void generate(Domain domain) {
		for(int i = 0; i < this.size; i++) {
			User[] rn = {domain.get(this.number.pop()), domain.get(this.number.pop()), domain.get(this.number.pop()), domain.get(this.number.pop()), domain.get(this.number.pop())};
			Individu tmp = new Individu(rn);
			this.population[i] = tmp;
			this.population[i].calculateFitness(this.user);
		}
		while(!this.number.isEmpty()) {
			this.leftover.add(this.domain.get(this.number.pop()));
		}
	}
	
	public void doCrossover(Individu a, Individu b) {
		/*
		 * one point crossover
		 * dengan tournament selection
		 */	
		Random rn = new Random();
		int start = rn.nextInt(5);
		while (start < a.getAlels().length) {
			User tmp = a.getAlels()[start];
			a.getAlels()[start] = b.getAlels()[start];
			b.getAlels()[start] = tmp;
			start++;
		}
		a.mutate();
		b.mutate();
		this.replacement(a, b);
	}
	
	private void replacement(Individu a, Individu b) {
		int last = idxLast();
		int last2 = idxSecondLast();
		this.replace(last, a);
		this.replace(last2, b);
		this.population[last].calculateFitness(this.user);
		this.population[last2].calculateFitness(this.user);
	}
	
	private Stack<Integer> generateRN(int size) {
		Stack<Integer> rn = new Stack<Integer>();
		for(int i = 0; i < size; i++) {
			rn.push(new Integer(i));
		}
		Collections.shuffle(rn);
		
		return rn;
	}
	
}


class ValueComparator implements Comparator<User> {
	User base;

    public ValueComparator(User base) {
        this.base = base;
    }

	@Override
	public int compare(User a, User b) {
		if (a.fitnessUser(this.base) >= b.fitnessUser(this.base)) {
            return -1;
        } else {
            return 1;
        }
	}
}