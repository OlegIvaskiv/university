package com.foxminded.university.spring.dto;

import java.util.Objects;

import com.foxminded.university.model.Lecture;

public class LectureRequestCreator {
	private int id;
	private int studentId;
	private int teacherId;
	private int dateId;
	private int audienceId;

	public LectureRequestCreator(int teacherId, int studentId, int audienceId, int dateId) {
		super();
		this.studentId = studentId;
		this.teacherId = teacherId;
		this.dateId = dateId;
		this.audienceId = audienceId;
	}

	public LectureRequestCreator(int id, int teacherId, int studentId, int audienceId, int dateId) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.teacherId = teacherId;
		this.dateId = dateId;
		this.audienceId = audienceId;
	}

	public LectureRequestCreator() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getDateId() {
		return dateId;
	}

	public void setDateId(int dateId) {
		this.dateId = dateId;
	}

	public int getAudienceId() {
		return audienceId;
	}

	public void setAudienceId(int audienceId) {
		this.audienceId = audienceId;
	}

	@Override
	public String toString() {
		return "Id : " + this.id + "\tStudent : " + this.studentId + "\tTeacher : " + this.teacherId + "\tDate : "
				+ this.dateId + "\tAudience : " + this.audienceId;
	}

	@Override
	public boolean equals(Object object) {
		if (object == this)
			return true;
		if (!(object instanceof Lecture)) {
			return false;
		}
		LectureRequestCreator lecture = (LectureRequestCreator) object;
		return this.id == lecture.id && Objects.equals(studentId, lecture.studentId)
				&& Objects.equals(teacherId, lecture.teacherId) && Objects.equals(dateId, lecture.dateId)
				&& Objects.equals(audienceId, lecture.audienceId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, studentId, teacherId, dateId, audienceId);
	}
}
