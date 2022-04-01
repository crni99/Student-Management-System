package com.crni99.studentms.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Please input First Name.")
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@NotBlank(message = "Please input Last Name.")
	@Column(name = "last_name", nullable = false)
	private String lastName;

	@NotBlank(message = "Please input Date of Birth.")
	@Column(name = "date_of_birth", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;

	@NotBlank(message = "Please input Email.")
	@Column(name = "email", nullable = false)
	private String email;

	@NotBlank(message = "Please input Index Number.")
	@Column(name = "index_number", nullable = false)
	private Integer indexNumber;

	@NotBlank(message = "Please input is it on Budget.")
	@Column(name = "is_on_budget", nullable = false)
	private Boolean isOnBudget;

	public Student() {
	}

	public Student(Long id, String firstName, String lastName, LocalDate dateOfBirth, String email, Integer indexNumber,
			Boolean isOnBudget) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.indexNumber = indexNumber;
		this.isOnBudget = isOnBudget;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(Integer indexNumber) {
		this.indexNumber = indexNumber;
	}

	public Boolean getIsOnBudget() {
		return isOnBudget;
	}

	public void setIsOnBudget(Boolean isOnBudget) {
		this.isOnBudget = isOnBudget;
	}

	@Override
	public String toString() {
		return "ID: " + id + "\nFirst name: " + firstName + "\nLast name: " + lastName + "\nDate of Birth: "
				+ dateOfBirth + "\nIndex number: " + indexNumber + "\nBudget financed: " + isOnBudget;
	}

}
