package com.example.TM.ResDto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotesListRes {
	public String message;
	public int responseCode;
	public List<NotesDto>  notesList;

}
