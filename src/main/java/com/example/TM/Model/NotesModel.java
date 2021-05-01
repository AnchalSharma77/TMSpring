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
@Table(name="Notes")
@Entity
public class NotesModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long nid;
	private Long tid;
	private String std;
	private String fileName;
	private String url;
	
}
