package com.example.TM.Model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="StudentLogin")
@Entity
public class StudentLoginModel {

//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
	@Id
	private String id;
	//private Long mobile;
	
	private String otp;

}

