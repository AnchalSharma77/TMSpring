package com.example.TM.Contrloller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.TM.ResDto.NotesRes;
import com.example.TM.Service.CentralService;

@RestController
@RequestMapping(value="/api",produces =" application/json")
public class NotesController extends CentralService{
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/UploadFile", method = RequestMethod.POST)
	public ResponseEntity<NotesRes> UploadFile(@RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("id") String id, @RequestParam("std") String std, @RequestParam(value="fileName", required = false) String fileName) throws Exception  {
		
		 try {
	            return notesService.UploadFile(id,std, fileName,file);

	        } catch (Exception e) {
	          //  LOGGER.error(e.getMessage());
	            throw new Exception(e.getMessage());
	        }
				
			
	}
	
			
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getNotesList", method = RequestMethod.GET)
	public ResponseEntity<NotesRes> getNotesList(@RequestParam("id") String id) throws Exception  {
		
		 try {
	            return notesService.getAllNotes(id);

	        } catch (Exception e) {
	          //  LOGGER.error(e.getMessage());
	            throw new Exception(e.getMessage());
	        }
				
			
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getNotesByStuId", method = RequestMethod.GET)
	public ResponseEntity<NotesRes> getNotesByStuId(@RequestParam("id") String id) throws Exception  {
		
		 try {
	            return notesService.getNotesByStuId(id);

	        } catch (Exception e) {
	          //  LOGGER.error(e.getMessage());
	            throw new Exception(e.getMessage());
	        }
				
			
	}

}
