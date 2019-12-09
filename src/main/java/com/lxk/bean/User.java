package com.lxk.bean;

import java.io.Serializable;

public class User implements Serializable{
	private int id;
	private String name;
	private String sex;
	private String phone;
	private String email;
	private String birthday;
	public User() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
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
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public User(int id, String name, String sex, String phone, String email,
			String birthday) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.phone = phone;
		this.email = email;
		this.birthday = birthday;
	}
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", sex=" + sex
				+ ", phone=" + phone + ", email=" + email + ", birthday="
				+ birthday + "]";
	}
}
