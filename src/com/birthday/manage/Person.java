package com.birthday.manage;

public class Person {
	private int personId;
	private String personName;
	private String birthdate;
	private String wishes;
	private String email;
	
	
	public Person(int personId,String personName, String birthdate, String wishes)
	{
		super();
		this.personId = personId;
		this.personName = personName;
		this.birthdate = birthdate;
		this.wishes= wishes;
		
	}

	public Person(String personName, String birthdate,String email)
	{
		super();
		this.personName = personName;
		this.birthdate = birthdate;
		this.email = email;
		
	}
	
	public Person(String personName, String birthdate)
	{
		super();
		this.personName = personName;
		this.birthdate = birthdate;
		
		
		
	}
	
	

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getBirthdate() {
		return birthdate;
	}
	
	public void setPersonEmail(String email) {
		this.email = email;
	}
	
	public String getPersonEmail() {
		return email;
	}

	public void setWishes(String wishes) {
		this.wishes = wishes;
	}


	public String toString() {
		return "Student [personId=" + personId + ", personName=" + personName + ", birthdate=" + birthdate
				+ ", wishes=" + wishes + "]";
	}


}
