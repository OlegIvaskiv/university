package com.foxminded.university.model;

import java.util.Objects;

import org.springframework.stereotype.Component;
@Component
public class Teacher {
	
    private int id;
    private Group group;
	

	private String name;
	private String phone;
	private String email;
	private String adress;
	
	public Teacher(int id,
			Group group,
			String name,
			String phone,
			String email,
			String adress) {
		super();
		this.id = id;
		this.group = group;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.adress = adress;
	}

	public Teacher() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
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
		return "Id : " + this.id + "Group : " + this.group + "\tName : " + this.name + "\tPhone : " + this.phone
				+ "\tEmail : " + this.email + "\tAdress : " + this.adress;
	}

	@Override
	public boolean equals(Object object) {
		if (object == this)
			return true;
		if (!(object instanceof Teacher)) {
			return false;
		}
		Teacher teacher = (Teacher) object;
		return this.id == teacher.id && Objects.equals(group, teacher.group) && Objects.equals(name, teacher.name)
				&& Objects.equals(phone, teacher.phone) && Objects.equals(email, teacher.email)
				&& Objects.equals(adress, teacher.adress);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, group, name, phone, email, adress);
	}

}
