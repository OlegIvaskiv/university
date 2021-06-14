package com.foxminded.university.model;

import java.util.Objects;

public class Group {
	private int id;
	private Course course;
	private String name;

	public void setId(int id) {
		this.id = id;
	}

	public Group(Course course, String name) {
		super();
		this.course = course;
		this.name = name;
	}

	public Group(int id, Course course, String name) {
		super();
		this.id = id;
		this.course = course;
		this.name = name;
	}

	public Group() {
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "GroupId : " + this.id + "\tCourse  : " + this.course + "\tGroupName : " + this.name;
	}

	@Override
	public boolean equals(Object object) {
		if (object == this)
			return true;
		if (!(object instanceof Group)) {
			return false;
		}
		Group group = (Group) object;
		return this.id == group.id && Objects.equals(name, group.name) && Objects.equals(course, group.course);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, course, name);
	}

}
