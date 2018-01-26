package project_sc.turtlez;

import java.util.ArrayList;

public class User {
	private int id;
	private String name;
	private boolean gender;
	private String faculty;
	private int batch;
	private String religion;
	private boolean isSmoker;
	private boolean isNeat;
	private ArrayList<String> interest;
	private User preference;
	
	public User() {
		
	}
	
	public User(String name){
		this.name = name;
	}
	
	public User(int id, String name, boolean gender, String faculty, int batch,
			String religion, boolean isSmoker, boolean isNeat, ArrayList<String> interest) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.faculty = faculty;
		this.batch = batch;
		this.religion = religion;
		this.isSmoker = isSmoker;
		this.isNeat = isNeat;
		this.interest = interest;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public int getBatch() {
		return batch;
	}

	public void setBatch(int batch) {
		this.batch = batch;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public boolean isSmoker() {
		return isSmoker;
	}

	public void setSmoker(boolean isSmoker) {
		this.isSmoker = isSmoker;
	}

	public boolean isNeat() {
		return isNeat;
	}

	public void setNeat(boolean isNeat) {
		this.isNeat = isNeat;
	}

	public ArrayList<String> getInterest() {
		return interest;
	}

	public void setInterest(ArrayList<String> interest) {
		this.interest = interest;
	}
	
	public void addInterest(String item) {
		if(this.interest == null) {
			this.interest = new ArrayList<String>();
		}
		this.interest.add(item);
	}
	
	public String deleteInterest(String item) {
		if(this.interest == null) {
			return null;
		} else {
			for(String i : interest){
				if(i.equalsIgnoreCase(item)){
					this.interest.remove(i);
					return i;
				}
			}
			return null;
		}
	}

	public User getPreference() {
		return preference;
	}

	public void setPreference(User preference) {
		this.preference = preference;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			return this.name.equals(((User)obj).name) && this.id == (((User)obj).id);
		}
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return (name.hashCode() + id) * 17 / 5;
	}

	@Override
	public String toString() {
		return String.format("User {name: %s, gender: %s, batch: %d, faculty: %s, religion: %s, interest: " 
				+ interest + "}", name, !gender ? "M" : "F", batch, faculty, religion);
	}
	
	public double fitnessUser(User user) {
		ArrayList<String> tmp = new ArrayList<String>();
		tmp.addAll(this.getInterest());
		tmp.retainAll(user.getInterest());
		if(tmp.isEmpty()) {
			return 0.0;
		}
		return ((double)tmp.size())/user.getInterest().size();
	}
	
}
