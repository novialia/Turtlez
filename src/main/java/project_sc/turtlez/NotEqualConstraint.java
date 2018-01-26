package project_sc.turtlez;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NotEqualConstraint implements Constraint{
	private List<User> scope;
	private User user1;
	private User user2;
	
	public NotEqualConstraint(User user1, User user2){
		this.user1 = user1;
		this.user2 = user2;
		this.scope = new ArrayList<User>();
		scope.add(user1);
		scope.add(user2);
	}

	@Override
	public List<User> getScope() {
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

	@Override
	public boolean isSatisfiedWith() {
		if (user1.equals(user2)) return false;
		return true;
	}

}
