package com.example.TM.Model;

import javax.persistence.Entity;
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
	private int std;
	private int fee;
}
