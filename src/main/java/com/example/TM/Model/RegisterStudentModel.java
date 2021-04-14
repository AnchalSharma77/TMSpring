package com.example.TM.Model;

import javax.persistence.Entity;
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
	private Long mobile;
	private String email;
	private String name;
	private String std;
	private String doj;//date of joining
	private int fee;// from static db 
	
	
}
