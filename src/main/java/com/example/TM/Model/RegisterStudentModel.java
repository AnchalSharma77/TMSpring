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
@Table(name="RegisteredStudentInfo")
@Entity
public class RegisterStudentModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Sid;
	private Long tid; //id of tutor
	private Long mobile;
	private String email;
	private String name;
	private String std;
	private String doj;//date of joining
	private int fee;// from static db 
	
	
}
