package com.foxminded.university.model;

import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class Audience {
	private int id;
	private int number;

	public Audience(int id, int number) {
		super();
		this.id = id;
		this.number = number;
	}

	public Audience() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		if (!(object instanceof Audience)) {
			return false;
		}
		Audience audience = (Audience) object;
		return this.id == audience.id && Objects.equals(number, audience.number);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, number);
	}
}
