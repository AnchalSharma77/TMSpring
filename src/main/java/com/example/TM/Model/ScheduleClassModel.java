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
@Table(name="ScheduleClass")
@Entity
public class ScheduleClassModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cid;
	private Long tid;
	private String std;
	private String name;
	private String date;
	private String msg;
	private String link;
}
