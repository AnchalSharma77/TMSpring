package com.example.TM.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="DueDetails")
@Entity
public class DueModel {
	@Id
	private Long id;
	private String email;
	private String mobile;
	private String lm ;// last month for which fee is paid
	private String year;

}
