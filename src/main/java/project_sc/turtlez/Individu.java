package project_sc.turtlez;

import java.util.Random;

public class Individu {
	private User[] alels;
	private int index;
	private double fitness;

	public Individu(User[] users) {
		this.alels = users;
		this.index = 0;
	}
	
	public Individu(int size) {
		this.alels = new User[size];
		this.index = 0;
	}
	
	public User[] getAlels() {
		return alels;
	}

	public void setAlels(User[] alels) {
		this.alels = alels;
	}
	
	public void replace(int index, User rep) {
		this.alels[index] = rep;
	}
	
	public int size() {
		return this.alels.length;
	}
	
	public boolean isFull() {
		return this.index == 4;
	}
	
	public double getFitness() {
		return fitness;
	}  

	
	/*
	 *	Hitung berapa banyak alel yang sudah memenuhi constraint
	 */
	public void calculateFitness(User user) {
		double counter = 0;
		for(User i: this.alels) {
			counter += i.fitnessUser(user);
		}
		
		this.fitness = counter/this.alels.length;
	}
	
	public void mutate() {
		Random rn = new Random();
		int a = rn.nextInt(5);
		int b = rn.nextInt(5);
		while(b == a) {
			b = rn.nextInt(5);
		}
		User temp = this.alels[a];
		this.alels[a] = this.alels[b];
		this.alels[b] = temp;
	}
	
}
