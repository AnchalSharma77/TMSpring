package com.example.TM.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="RegisterTutor")
public class RegisterTutorModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	String fn;
	String ln;
	String email;
	String mobile;
	String pass;
}


/*
@NotNull(message = "Name cannot be null")
@Column(nullable = false, length = 64)
String fn;
@Column
String ln;

@NotNull(message = "email cannot be null")
@Column(nullable = false, unique = true, length = 45)
@Email(message = "Email should be valid")
String email;

@NotNull(message = "Mobile cannot be null")
@Column(nullable = false, unique = true, length = 10)
@Pattern(regexp="(/^[0-9]{10} $/)")
String mobile;

@NotNull(message = "password cannot be null")
@Column(nullable = false, length = 8)
String pass;

@NotNull(message = "password cannot be null")
String matchpass;
*/