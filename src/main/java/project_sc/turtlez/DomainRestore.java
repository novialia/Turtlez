package project_sc.turtlez;

import java.util.*;

/**
 * Provides informations which might be useful for a caller of a
 * constraint propagation algorithm. It maintains old domains for
 * variables and provides means to restore the initial state of the
 * CSP (before domain reduction started). Additionally, a flag indicates
 * whether an empty domain has been found during propagation. 
 * @author Ruediger Lunde
 *
 */
public class DomainRestore {
	private HashMap<User, Domain> savedDomains;
	private HashSet<User> affectedUsers;
	private boolean emptyDomainObserved;

	public DomainRestore() {
		savedDomains = new HashMap<User, Domain>();
		affectedUsers = new HashSet<User>();
	}

	public void clear() {
		savedDomains.clear();
		affectedUsers.clear();
	}

	public boolean isEmpty() {
		return savedDomains.isEmpty();
	}

	/**
	 * Stores the specified domain for the specified variable if a domain has
	 * not yet been stored for the variable.
	 */
	public void storeDomainFor(User user, Domain domain) {
		if (!affectedUsers.contains(user)) {
			savedDomains.put(user, domain);
			affectedUsers.add(user);
		}
	}

	public void setEmptyDomainFound(boolean b) {
		emptyDomainObserved = b;
	}

	/**
	 * Can be called after all domain information has been collected to reduce
	 * storage consumption.
	 * 
	 * @return this object, after removing one hashtable.
	 */
	public DomainRestore compactify() {
		affectedUsers = null;
		return this;
	}

	public boolean isEmptyDomainFound() {
		return emptyDomainObserved;
	}

	public HashMap<User, Domain> getSavedDomains() {
		return savedDomains;
	}
	
	public void restoreDomains(CSP csp) {
		for (Map.Entry<User, Domain> conn : getSavedDomains().entrySet())
			csp.setDomain(conn.getKey(), conn.getValue());
	}

}