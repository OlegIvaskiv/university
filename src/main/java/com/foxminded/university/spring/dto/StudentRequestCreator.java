package com.foxminded.university.spring.dto;

import java.util.Objects;

import com.foxminded.university.model.Student;

public class StudentRequestCreator {
	private int id;
	private int groupId;
	private String name;
	private String phone;
	private String email;
	private String adress;

	public StudentRequestCreator(int groupId, String name, String phone, String email, String adress) {
		super();
		this.groupId = groupId;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.adress = adress;
	}

	public StudentRequestCreator(int id, int groupId, String name, String phone, String email, String adress) {
		super();
		this.id = id;
		this.groupId = groupId;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.adress = adress;
	}

	public StudentRequestCreator() {
	}

	public int getGroupId() {
		return groupId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	@Override
	public String toString() {
		return "Id : " + this.id + "GroupId : " + this.groupId + "\tName : " + this.name + "\tPhone : " + this.phone
				+ "\tEmail : " + this.email + "\tAdress : " + this.adress;
	}

	@Override
	public boolean equals(Object object) {
		if (object == this)
			return true;
		if (!(object instanceof Student)) {
			return false;
		}
		StudentRequestCreator studentDto = (StudentRequestCreator) object;
		return this.id == studentDto.id && Objects.equals(name, studentDto.name)
				&& Objects.equals(groupId, studentDto.groupId) && Objects.equals(phone, studentDto.phone)
				&& Objects.equals(email, studentDto.email) && Objects.equals(adress, studentDto.adress);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, groupId, name, phone, email, adress);
	}
}
