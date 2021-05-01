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
@Table(name="DueDetails")
@Entity
public class DueModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long did;
	private Long tid;
	private Long sid;
	private Long mobile;
	private String email;
	private String ld ;// last date for which fee is paid
	private Long dm;//total number of months for which fee is due
	private Long dueFee;//calculated using due month and fee per month of the particular standard

}
