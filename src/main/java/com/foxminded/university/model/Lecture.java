package com.foxminded.university.model;

import java.util.Objects;

public class Lecture {
	private int id;
	private Student student;
	private Teacher teacher;
	private Date date;
	private Audience audience;

	public Lecture(Teacher teacher, Student student, Audience audience, Date date) {
		super();
		this.student = student;
		this.teacher = teacher;
		this.date = date;
		this.audience = audience;
	}

	public Lecture(int id, Teacher teacher, Student student, Audience audience, Date date) {
		super();
		this.id = id;
		this.student = student;
		this.teacher = teacher;
		this.date = date;
		this.audience = audience;
	}

	public Lecture() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Audience getAudience() {
		return audience;
	}

	public void setAudience(Audience audience) {
		this.audience = audience;
	}

	@Override
	public String toString() {
		return "Id : " + this.id + "\tStudent : " + this.student + "\tTeacher : " + this.teacher + "\tDate : "
				+ this.date + "\tAudience : " + this.audience;
	}

	@Override
	public boolean equals(Object object) {
		if (object == this)
			return true;
		if (!(object instanceof Lecture)) {
			return false;
		}
		Lecture lecture = (Lecture) object;
		return this.id == lecture.id && Objects.equals(student, lecture.student)
				&& Objects.equals(teacher, lecture.teacher) && Objects.equals(date, lecture.date)
				&& Objects.equals(audience, lecture.audience);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, student, teacher, date, audience);
	}
}
