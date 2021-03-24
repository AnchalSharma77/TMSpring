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
@Table(name="StudentInfo")
@Entity
public class StudentModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;//auto-increment
	private Long phone;
	private String name;
	private String std;
	private String doj;//date of joining
	private int fee;// from static db 
	
	
}
