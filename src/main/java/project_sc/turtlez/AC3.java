package project_sc.turtlez;

import java.util.*;

public class AC3 {
	/**
	 * Makes a CSP consisting of binary constraints arc-consistent.
	 * 
	 * @return An object which indicates success/failure and contains data to
	 *         undo the operation.
	 */
	public DomainRestore reduceDomains(CSP csp) {
		DomainRestore result = new DomainRestore();
		Queue<User> queue = new LinkedList<User>();
		for (User user : csp.getVariables()) {
			queue.add(user);
			Domain domain = csp.getDomain(user);
			result.storeDomainFor(user, domain);
		}
			
		reduceDomains(queue, csp, result);
		return result.compactify();
	}

	/**
	 * Reduces the domain of the specified variable to the specified value and
	 * reestablishes arc-consistency. It is assumed that the provided CSP is
	 * arc-consistent before the call.
	 * 
	 * @return An object which indicates success/failure and contains data to
	 *         undo the operation.
	 */
	public DomainRestore reduceDomains(User user, User value, CSP csp) {
		DomainRestore result = new DomainRestore();
		Domain domain = csp.getDomain(user);
		if (domain.contains(value)) {
			if (domain.size() > 1) {
				Queue<User> queue = new LinkedList<User>();
				queue.add(user);
				result.storeDomainFor(user, domain);
				csp.setDomain(user, new Domain(new User[] { value }));
				reduceDomains(queue, csp, result);
			}
		} else {
			result.setEmptyDomainFound(true);
		}
		return result.compactify();
	}

	private void reduceDomains(Queue<User> queue, CSP csp, DomainRestore info) {
		while (!queue.isEmpty()) {
			User user = queue.remove();
			for (Constraint constraint : csp.getConstraints(user)) {
				if (constraint.getScope().size() == 2) {
					User neighbor = csp.getNeighbor(user, constraint);
					if (revise(neighbor, user, constraint, csp, info)) {
						if (csp.getDomain(neighbor).isEmpty()) {
							info.setEmptyDomainFound(true);
							return;
						}
						queue.add(neighbor);
					}
				}
			}
		}
	}

	private boolean revise(User useri, User userj, Constraint constraint,
			CSP csp, DomainRestore info) {
		boolean revised = false;
		for (User iValue : csp.getDomain(useri)) {
			constraint.setUser2(iValue);
			boolean consistentExtensionFound = false;
			for (User jValue : csp.getDomain(userj)) {
				constraint.setUser1(jValue);
				if (constraint.isSatisfiedWith()) {
					consistentExtensionFound = true;
					break;
				}
			}
			if (!consistentExtensionFound) {
				info.storeDomainFor(useri, csp.getDomain(useri));
				csp.removeValueFromDomain(useri, iValue);
				revised = true;
			}
		}
		constraint.clean();
		return revised;
	}
}
