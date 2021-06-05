package com.example.TM.Contrloller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TM.ReqDto.EditDescReq;
import com.example.TM.ResDto.DescRes;
import com.example.TM.ResDto.EditDescRes;
import com.example.TM.Service.CentralService;

@RestController
@RequestMapping(value="/api",produces =" application/json")
public class EditDescController extends CentralService {
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getAbt", method = RequestMethod.GET)
	public ResponseEntity<DescRes> getAbout(@RequestParam("id") String id) throws Exception  {
	
		 try {
			 
	            return editDescService.getAbout(id);

	        } catch (Exception e) {
	            throw new Exception(e.getMessage());
	        }
				
			
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/editAbout", method = RequestMethod.POST)
	public ResponseEntity<EditDescRes> editAbout(@RequestParam("id") String id, @RequestBody EditDescReq about) throws Exception  {
	
		 try {
			 
	            return editDescService.editAbout(id,about);

	        } catch (Exception e) {
	            throw new Exception(e.getMessage());
	        }
				
			
	}
	

}
