package project_sc.turtlez;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SolveDormate {
	private double avgRate;
	private List<User> users;
	private List<Double> compatibilityRate;
	private User me;
	
	public SolveDormate(){
		users = new ArrayList<User>();
		compatibilityRate = new ArrayList<Double>();
	}

	public void solveWithCmd(){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String name = "";
		
		try {
			System.out.println("Enter your name: ");
			name = br.readLine();
			
		} catch (IOException e) {
			System.out.println("There's something wrong with your input. Please try again.");
		}
		
		System.out.println("Welcome, " + name + "!");
		
		CSP csp = new CSP();
		UserGenerator ug = new UserGenerator();
		List<User> dom = ug.generate();
		User usr = ug.generateMe(name);
		
		if(usr == null){
			System.out.println("Whoops! You're not in our database. Do you even belong here?");
			return;
		}
		
		System.out.println("Here's your information: ");
		System.out.println(usr);
		
		dom.remove(usr);
		Domain d1 = new Domain(dom);
		
		for(User u : csp.getVariables()) {
			if(u.equals(csp.getMe())) {
				ArrayList<User> meDomain = new ArrayList<User>();
				meDomain.add(usr);
				Domain d = new Domain(meDomain);
				csp.setDomain(u, d);
			} else {
				csp.setDomain(u, d1);
			}
		}
		
		AC3 ho = new AC3();
		DomainRestore dr = ho.reduceDomains(csp);
		if(!dr.isEmpty()){
			if(dr.isEmptyDomainFound()){
				System.out.println("EMPTY DOMAIN FOUND. NO SOLUTION.");
			} else {
				HashSet<User> hs = new HashSet<User>();
				List<Domain> remains = csp.getRemainingDomains();
				for(Domain d : remains){
					hs.addAll(d.getAll());
				}
				hs.remove(usr);
				List<User> users = new ArrayList<User>(hs);
				Domain result = new Domain(users);
				Population n = new Population(result, usr);
				Individu res;
				if(result.size() < 10) {
					res = n.smallDomainSolution(result.getAll());
				}	
				else {
					GeneticAlgorithmSearch ga = new GeneticAlgorithmSearch(n);
					res = ga.doSearch();
					for(User u : res.getAlels()) {
						n.addLetfover(u);
					}
					res = n.smallDomainSolution(n.getLeftover());
				}
				res.calculateFitness(usr);
				System.out.printf("Average compatibility rate: %.2f", (res.getFitness() + 6)/7*100);
				System.out.println("\nYour top 5 recommended roommate:");
				for(User i1 : res.getAlels()) {
					System.out.println(i1);
					System.out.printf("Compatibility rate: %.2f\n", (i1.fitnessUser(usr) + 6)/7*100);
				}
			}
		}
	}
	
	public void solve(String name){
		
		CSP csp = new CSP();
		UserGenerator ug = new UserGenerator();
		List<User> dom = ug.generate();
		User usr = ug.generateMe(name);
		
		dom.remove(usr);
		Domain d1 = new Domain(dom);
		
		for(User u : csp.getVariables()) {
			if(u.equals(csp.getMe())) {
				ArrayList<User> meDomain = new ArrayList<User>();
				meDomain.add(usr);
				Domain d = new Domain(meDomain);
				csp.setDomain(u, d);
			} else {
				csp.setDomain(u, d1);
			}
		}
		
		AC3 ho = new AC3();
		DomainRestore dr = ho.reduceDomains(csp);
		if(!dr.isEmpty()){
			if(dr.isEmptyDomainFound()){
				System.out.println("EMPTY DOMAIN FOUND. NO SOLUTION.");
			} else {
				HashSet<User> hs = new HashSet<User>();
				List<Domain> remains = csp.getRemainingDomains();
				for(Domain d : remains){
					hs.addAll(d.getAll());
				}
				hs.remove(usr);
				List<User> users = new ArrayList<User>(hs);
				Domain result = new Domain(users);
				Population n = new Population(result, usr);
				Individu res;
				if(result.size() < 10) {
					res = n.smallDomainSolution(result.getAll());
				}	
				else {
					GeneticAlgorithmSearch ga = new GeneticAlgorithmSearch(n);
					res = ga.doSearch();
					for(User u : res.getAlels()) {
						n.addLetfover(u);
					}
					res = n.smallDomainSolution(n.getLeftover());
				}
				res.calculateFitness(usr);
				avgRate = (res.getFitness() + 6)/7*100;
				System.out.printf("Average compatibility rate: %.2f", avgRate);
				System.out.println("\nYour top 5 recommended roommate:");
				this.me = usr;
				for(User i1 : res.getAlels()) {
					System.out.println(i1);
					System.out.printf("Compatibility rate: %.2f\n", (i1.fitnessUser(usr) + 6)/7*100);
					this.users.add(i1);
					compatibilityRate.add((i1.fitnessUser(usr) + 6)/7*100);
				}
			}
		}
	}

	public double getAvgRate() {
		return avgRate;
	}

	public List<User> getUsers() {
		return users;
	}

	public List<Double> getCompatibilityRate() {
		return compatibilityRate;
	}
	
	public User getMe() {
		return me;
	}
	
	
}
