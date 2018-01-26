package project_sc.turtlez;

import java.util.ArrayList;
import java.util.List;

public class UserConstraint implements Constraint {
	private User user1;
	private User user2;
	private List<User> scope;
	
	public UserConstraint(User user1, User user2){
		this.user1 = user1;
		this.user2 = user2;
		this.scope = new ArrayList<User>();
		scope.add(user1);
		scope.add(user2);
	}
	
	public List<User> getScope(){
		return scope;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}
	
	public void clean() {
		this.user1 = scope.get(0);
		this.user2 = scope.get(1);
	}

	public boolean isSatisfiedWith(){
		if(this.user2 == null) return true;
		if(this.user1.getGender() != this.user2.getGender()) return false;
		if(this.user1.isSmoker() && !this.user2.isSmoker()) return false;
		User pref = this.user1.getPreference();
		if(pref == null) pref = user1;
		if(pref.getBatch() != this.user2.getBatch() || user1.getBatch() != this.user2.getBatch()) return false;
		if(pref.getReligion() != this.user2.getReligion() || user1.getReligion() != this.user2.getReligion()) return false;
		if((pref.isNeat() || user1.isNeat()) && !this.user2.isNeat()) return false;
		if((!pref.isSmoker() || !user1.isSmoker()) && this.user2.isSmoker()) return false;
		return true;
	}

}
