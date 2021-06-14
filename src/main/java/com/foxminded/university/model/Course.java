package com.foxminded.university.model;

import java.util.Objects;

public class Course {
	private int id;

	private String name;

	public Course(String name) {
		super();
		this.name = name;
	}

	public Course(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Course() {
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CourseId : " + this.id + "\tCourseName : " + this.name;
	}

	@Override
	public boolean equals(Object object) {
		if (object == this)
			return true;
		if (!(object instanceof Course)) {
			return false;
		}
		Course course = (Course) object;
		return this.id == course.id && Objects.equals(name, course.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

}
