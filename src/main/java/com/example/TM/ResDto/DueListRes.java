package com.example.TM.ResDto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DueListRes {
	public String message;
	public int responseCode;
	List<DueDto> dueList;

}
