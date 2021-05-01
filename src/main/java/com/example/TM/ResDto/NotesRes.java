package com.example.TM.ResDto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotesRes {
	public String message;
	public int responseCode;
	//public String fileUrl;
	public List<NotesDto>  notesList;

}
