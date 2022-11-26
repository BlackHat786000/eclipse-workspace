package com.collection.comparable;

public class User implements Comparable<User> {
	String name;
	int id;

	public User(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", id=" + id + "]";
	}

	@Override
	public int compareTo(User o) {
		
		return -(name.compareTo(o.getName()));
		
	}

}
