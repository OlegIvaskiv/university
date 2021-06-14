package com.foxminded.university.spring.dto;

import java.util.Objects;

public class AudienceRequestCreator {
	private int id;
	private int number;

	public void setId(int id) {
		this.id = id;
	}

	public AudienceRequestCreator(int id, int number) {
		super();
		this.id = id;
		this.number = number;
	}

	public AudienceRequestCreator(int number) {
		super();

		this.number = number;
	}

	public AudienceRequestCreator() {
	}

	public int getId() {
		return id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Id : " + this.id + "\tNumber : " + this.number;
	}

	@Override
	public boolean equals(Object object) {
		if (object == this)
			return true;
		if (!(object instanceof AudienceRequestCreator)) {
			return false;
		}
		AudienceRequestCreator audience = (AudienceRequestCreator) object;
		return this.id == audience.id && Objects.equals(number, audience.number);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, number);
	}
}
