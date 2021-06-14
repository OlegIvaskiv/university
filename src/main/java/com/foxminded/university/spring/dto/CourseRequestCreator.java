package com.foxminded.university.spring.dto;

import java.util.Objects;

public class CourseRequestCreator {
	private int id;

	private String name;

	public CourseRequestCreator(String name) {
		super();
		this.name = name;
	}

	public CourseRequestCreator(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public CourseRequestCreator() {
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
		return "CourseDtoId : " + this.id + "\tCourseDtoName : " + this.name;
	}

	@Override
	public boolean equals(Object object) {
		if (object == this)
			return true;
		if (!(object instanceof CourseRequestCreator)) {
			return false;
		}
		CourseRequestCreator courseDto = (CourseRequestCreator) object;
		return this.id == courseDto.id && Objects.equals(name, courseDto.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

}
