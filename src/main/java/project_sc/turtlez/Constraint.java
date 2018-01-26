package project_sc.turtlez;

import java.util.List;

public interface Constraint {
	
	List<User> getScope();
	boolean isSatisfiedWith();
	void clean();
	void setUser1(User user);
	void setUser2(User user);
	User getUser1();
	User getUser2();

}
