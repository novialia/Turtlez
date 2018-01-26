package project_sc.turtlez;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

public class CSP {
	private List<User> variables;
	private List<Domain> domains;
	private List<Constraint> constraints;
	private final User me = new User("me");
	
	private Hashtable<User, Integer> varIndexHash;
	private Hashtable<User, List<Constraint>> cnet;

	public CSP() {
		List<User> vars = collectVariables();
		variables = new ArrayList<User>(vars.size());
		domains = new ArrayList<Domain>(vars.size());
		constraints = new ArrayList<Constraint>();
		varIndexHash = new Hashtable<User, Integer>();
		cnet = new Hashtable<User, List<Constraint>>();
		Domain emptyDomain = new Domain(new ArrayList<User>(0));
		int index = 0;
		for (User var : vars) {
			variables.add(var);
			domains.add(emptyDomain);
			varIndexHash.put(var, index++);
			cnet.put(var, new ArrayList<Constraint>());
		}
		
		for(User i : variables){
			if(i.equals(me)) continue;
			addConstraint(new UserConstraint(me, i));
		}
		for (User i : variables) {
			for (User j : variables) {
				if(i.equals(j)) continue;
				addConstraint(new NotEqualConstraint(i, j));
			}
		}
	}

	public List<User> getVariables() {
		return Collections.unmodifiableList(variables);
	}

	public int indexOf(User var) {
		return varIndexHash.get(var);
	}

	public Domain getDomain(User var) {
		return domains.get(varIndexHash.get(var));
	}
	
	public List<Domain> getRemainingDomains() {
		return domains;
	}

	public void setDomain(User var, Domain domain) {
		domains.set(indexOf(var), domain);
	}

	public void removeValueFromDomain(User var, User value) {
		Domain currDomain = getDomain(var);
		List<User> values = new ArrayList<User>(currDomain.size());
		for (User v : currDomain)
			if (!v.equals(value))
				values.add(v);
		setDomain(var, new Domain(values));
	}

	public List<Constraint> getConstraints() {
		return constraints;
	}

	public List<Constraint> getConstraints(User var) {
		return cnet.get(var);
	}

	public void addConstraint(Constraint constraint) {
		constraints.add(constraint);
		for (User var : constraint.getScope())
			cnet.get(var).add(constraint);
	}

	public User getNeighbor(User var, Constraint constraint) {
		List<User> scope = constraint.getScope();
		if (scope.size() == 2) {
			if (var == scope.get(0))
				return scope.get(1);
			else if (var == scope.get(1))
				return scope.get(0);
		}
		return null;
	}

	public User getMe() {
		return me;
	}

	public CSP copyDomains() {
		CSP result = new CSP();
		result.variables = variables;
		result.domains = new ArrayList<Domain>(domains.size());
		result.domains.addAll(domains);
		result.constraints = constraints;
		result.varIndexHash = varIndexHash;
		result.cnet = cnet;
		return result;
	}
	
	public List<User> collectVariables(){
		List<User> vars = new ArrayList<User>();
		vars.add(me);
		vars.add(new User("friendOne"));
		vars.add(new User("friendTwo"));
		vars.add(new User("friendThree"));
		vars.add(new User("friendFour"));
		vars.add(new User("friendFive"));
		return vars;
	}
}
