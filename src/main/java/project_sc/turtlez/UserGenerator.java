package project_sc.turtlez;

import java.util.ArrayList;
import java.util.List;

public class UserGenerator {
	private List<User> users;
	
	public UserGenerator(){
		generate();
	}
	
	public List<User> generate() {
		
		if(users != null && !users.isEmpty()) return users;
		
		String[] interests = {"basket", "renang", "buku", "foto", "bulutangkis", "volley", "belajar", "ngoding",
		                      "nonton", "lari", "nari", "menyanyi", "makan", "travel", "sports"};
		String[] religion = {"muslim", "christian", "catholic", "buddha", "hindu", "other"};
		String[] faculty = {"fasilkom", "fik", "fia", "fh", "fpsi"};
		String[] firsts = {"joe", "jane", "jack", "john", "anne", "marie", "kai", "alex",
				"delta", "alpha", "gamma", "ray", "daniel", "debbie", "may"};
		boolean[] gender = {false, true, false, false, true, true, false, false, true, true, false, false, false, true, true};
		String[] lasts = {"doe", "dae", "mane", "duke", "michaels", "mcken", "mcavoy", "park", "jameson",
				"mckay", "mckensie", "cassie", "mcgrin", "augusto", "delanie"};
		
		users = new ArrayList<User>();
		int a = 0;
		for(int i = 0; i < 225; i++){
			User u = new User();
			u.setId(i + 11);
			u.setName(firsts[a%firsts.length] + " " + lasts[i%lasts.length]);
			u.setReligion(religion[i%religion.length]);
			u.setBatch(i%3 + 1);
			u.setGender(gender[a%gender.length]);
			u.setNeat(i % 2 == 0 ? true : false);
			u.setSmoker(i % 2 == 1 ? true : false);
			u.setFaculty(faculty[i%faculty.length]);
			u.addInterest(interests[i%interests.length]);
			u.addInterest(interests[(i+1)%interests.length]);
			u.addInterest(interests[(i+2)%interests.length]);
			if(i%3 == 0) u.addInterest(interests[(i+7)%interests.length]);
			users.add(u);
			
			if(i % lasts.length == 0) a++;
		}
		
		return users;
	}
	
	public User generateMe(String name){
		for(User u : users){
			if (name.toLowerCase().equals(u.getName())) {
				return u;
			}
		}
		return null;
	}
	
	public String[] generateNames() {
		String[] names = new String[users.size()];
		for(int i = 0; i < names.length; i++) {
			names[i] = users.get(i).getName();
		}
		return names;
	}

}
