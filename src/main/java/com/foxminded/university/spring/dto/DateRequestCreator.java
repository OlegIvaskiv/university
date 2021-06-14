package com.foxminded.university.spring.dto;

import java.util.Objects;

import com.foxminded.university.model.Date;

public class DateRequestCreator {

	private int id;
	private String day;
	private String mounth;
	private String year;

	public DateRequestCreator(String day, String mounth, String year) {
		super();
		this.day = day;
		this.mounth = mounth;
		this.year = year;
	}

	public DateRequestCreator(int id, String day, String mounth, String year) {
		super();
		this.id = id;
		this.day = day;
		this.mounth = mounth;
		this.year = year;
	}

	public DateRequestCreator() {
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
		DateRequestCreator dateDto = (DateRequestCreator) object;
		return this.id == dateDto.id && Objects.equals(day, dateDto.day) && Objects.equals(mounth, dateDto.mounth)
				&& Objects.equals(year, dateDto.year);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, day, mounth, year);
	}
}
