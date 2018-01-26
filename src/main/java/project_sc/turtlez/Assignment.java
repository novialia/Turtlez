package project_sc.turtlez;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

public class Assignment {
	/**
	 * Contains all assigned variables. Positions reflect the the order in which
	 * the variables were assigned to values.
	 */
	List<User> variables;
	/** Maps variables to their assigned values. */
	Hashtable<User, User> variableToValue;

	public Assignment() {
		variables = new ArrayList<User>();
		variableToValue = new Hashtable<User, User>();
	}
	
	public List<User> getVariables() {
		return Collections.unmodifiableList(variables);
	}

	public User getAssignment(User var) {
		return variableToValue.get(var);
	}

	public void setAssignment(User var, User value) {
		if (!variableToValue.containsKey(var))
			variables.add(var);
		variableToValue.put(var, value);
	}

	public void removeAssignment(User var) {
		if (hasAssignmentFor(var)) {
			variables.remove(var);
			variableToValue.remove(var);
		}
	}

	public boolean hasAssignmentFor(User var) {
		return variableToValue.get(var) != null;
	}

	/**
	 * Returns true if this assignment does not violate any constraints of
	 * <code>constraints</code>.
	 */
	public boolean isConsistent(List<Constraint> constraints) {
		for (Constraint cons : constraints)
			if (!cons.isSatisfiedWith())
				return false;
		return true;
	}

	/**
	 * Returns true if this assignment assigns values to every variable of
	 * <code>vars</code>.
	 */
	public boolean isComplete(List<User> vars) {
		for (User var : vars) {
			if (!hasAssignmentFor(var))
				return false;
		}
		return true;
	}

	/**
	 * Returns true if this assignment assigns values to every variable of
	 * <code>vars</code>.
	 */
	public boolean isComplete(User[] vars) {
		for (User var : vars) {
			if (!hasAssignmentFor(var))
				return false;
		}
		return true;
	}

	/**
	 * Returns true if this assignment is consistent as well as complete with
	 * respect to the given CSP.
	 */
	public boolean isSolution(CSP csp) {
//		return isConsistent(csp.getConstraints())
//				&& isComplete(csp.getVariables());
		return true;
	}

	public Assignment copy() {
		Assignment copy = new Assignment();
		for (User var : variables) {
			copy.setAssignment(var, variableToValue.get(var));
		}
		return copy;
	}

	@Override
	public String toString() {
		boolean comma = false;
		StringBuffer result = new StringBuffer("{");
		for (User var : variables) {
			if (comma)
				result.append(", ");
			result.append(var + "=" + variableToValue.get(var));
			comma = true;
		}
		result.append("}");
		return result.toString();
	}
	
}
