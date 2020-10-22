package es.uco.pw.bulletinBoard.business;

import java.time.LocalDate;
import java.util.ArrayList;

public class User {
	
	private Integer id;
	private String name;
	private String lastName;
	private String email;
	private String password;
	private LocalDate dateOfBirth;
	private ArrayList<String> interests;
	
	public User(Integer id, String name, String lastName, String email, String password, LocalDate dateOfBirth, ArrayList<String> interests) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.interests = interests;
	}
	
	public User(String name, String lastName, String email, String password, LocalDate dateOfBirth, ArrayList<String> interests) {
		this.id = null;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.interests = interests;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public ArrayList<String> getInterests() {
		return interests;
	}

	public void setInterests(ArrayList<String> interests) {
		this.interests = interests;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((interests == null) ? 0 : interests.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) 										return true;
		if (obj == null) 										return false;
		if (getClass() != obj.getClass()) 						return false;
		
		User other = (User) obj;
		
		if (dateOfBirth == null && other.dateOfBirth != null)	return false;
		else if (!dateOfBirth.equals(other.dateOfBirth))		return false;
		
		if (email == null && other.email != null) 				return false;
		else if (!email.equals(other.email)) 					return false;
		
		if (id == null && other.id != null) 					return false;
		else if (!id.equals(other.id)) 							return false;
		
		if (interests == null && other.interests != null) 		return false;
		else if (!interests.equals(other.interests)) 			return false;
		
		if (lastName == null && other.lastName != null) 		return false;
		else if (!lastName.equals(other.lastName)) 				return false;
		
		if (name == null && other.name != null) 				return false;	
		else if (!name.equals(other.name)) 						return false;
		
		if (password == null && other.password != null) 		return false;
		else if (!password.equals(other.password)) 				return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", lastName=" + lastName + ", email=" + email + ", password="
				+ password + ", dateOfBirth=" + dateOfBirth + ", interests=" + interests + "]";
	}
}
