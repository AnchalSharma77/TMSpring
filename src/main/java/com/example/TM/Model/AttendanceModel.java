package com.example.TM.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="Attendance")
@Entity
public class AttendanceModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Aid;
	private Long tid;//if student is registered with multiple tutors
	private Long mobile;
	private String email;
	private String name;
	private String date;
	private String attendance;
	
	
}
