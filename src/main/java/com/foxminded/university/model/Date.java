package com.foxminded.university.model;

import java.util.Objects;

public class Date {

	private int id;
	private String day;
	private String mounth;
	private String year;

	public Date(String day, String mounth, String year) {
		super();
		this.day = day;
		this.mounth = mounth;
		this.year = year;
	}

	public Date(int id, String day, String mounth, String year) {
		super();
		this.id = id;
		this.day = day;
		this.mounth = mounth;
		this.year = year;
	}

	public Date() {
	}

	public int getId() {
		return id;
	}

	public String getDay() {
		return day;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMounth() {
		return mounth;
	}

	public void setMounth(String mounth) {
		this.mounth = mounth;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Id : " + this.id + "\tDay : " + this.day + "\tMounth : " + this.mounth + "\tYear : " + this.year;
	}

	@Override
	public boolean equals(Object object) {
		if (object == this)
			return true;
		if (!(object instanceof Date)) {
			return false;
		}
		Date date = (Date) object;
		return this.id == date.id && Objects.equals(day, date.day) && Objects.equals(mounth, date.mounth)
				&& Objects.equals(year, date.year);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, day, mounth, year);
	}

}
