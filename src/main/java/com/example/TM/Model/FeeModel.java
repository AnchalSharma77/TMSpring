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
@Table(name="FeeDetails")
@Entity
public class FeeModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Fid;
	private int std;
	private int fee;
	private Long tid;
}
