package com.foxminded.university.spring.dto;

import java.util.Objects;

import com.foxminded.university.model.Group;

public class GroupRequestCreator {
	private int id;
	private int courseId;
	private String name;

	public void setId(int id) {
		this.id = id;
	}

	public GroupRequestCreator(int courseId, String name) {
		super();
		this.courseId = courseId;
		this.name = name;
	}

	public GroupRequestCreator(int id, int courseId, String name) {
		super();
		this.id = id;
		this.courseId = courseId;
		this.name = name;
	}

	public GroupRequestCreator() {
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
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
		return "GroupId : " + this.id + "\tCourseId  : " + this.courseId + "\tGroupName : " + this.name;
	}

	@Override
	public boolean equals(Object object) {
		if (object == this)
			return true;
		if (!(object instanceof Group)) {
			return false;
		}
		GroupRequestCreator groupDto = (GroupRequestCreator) object;
		return this.id == groupDto.id && Objects.equals(name, groupDto.name)
				&& Objects.equals(courseId, groupDto.courseId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, courseId, name);
	}

}
