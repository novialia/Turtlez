package project_sc.turtlez;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Domain implements Iterable<User>{
	private User[] values;

	public Domain(List<User> values) {
		this.values = new User[values.size()];
		for (int i = 0; i < values.size(); i++)
			this.values[i] = values.get(i);
	}

	public Domain(User[] values) {
		this.values = new User[values.length];
		for (int i = 0; i < values.length; i++)
			this.values[i] = values[i];
	}

	public int size() {
		return values.length;
	}

	public User get(int index) {
		return values[index];
	}
	
	public List<User> getAll() {
		return Arrays.asList(values);
	}

	public boolean isEmpty() {
		return values.length == 0;
	}

	public boolean contains(User value) {
		for (User v : values)
			if (v.equals(value))
				return true;
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Domain) {
			Domain d = (Domain) obj;
			if (d.size() != values.length)
				return false;
			else
				for (int i = 0; i < values.length; i++)
					if (!values[i].equals(d.values[i]))
						return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 9; // arbitrary seed value
		int multiplier = 13; // arbitrary multiplier value
		for (int i = 0; i < values.length; i++)
			hash = hash * multiplier + values[i].hashCode();
		return hash;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer("{");
		boolean comma = false;
		for (Object value : values) {
			if (comma)
				result.append(", ");
			result.append(value.toString());
			comma = true;
		}
		result.append("}");
		return result.toString();
	}

	@Override
	public Iterator<User> iterator() {
		return Arrays.asList(values).iterator();
	}

}
