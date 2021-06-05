package com.example.TM.Contrloller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TM.ResDto.DueListRes;
import com.example.TM.ResDto.DueRes;
import com.example.TM.Service.CentralService;
import com.example.TM.Util.ResopnseCodes;

@RestController
@RequestMapping(value="/api",produces =" application/json")
public class DueController extends CentralService{
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/totalDue")
	public Long getTotalDue(@RequestParam String id) {
		return dueService.getDuesSum(id);
	}
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/dueById", method = RequestMethod.GET)
	public ResponseEntity<DueRes> getDueById( @RequestParam String sid,@RequestParam String eid) throws Exception  {
		 try {
			 	
			 return dueService.getDueById(sid,eid);

	        } catch (Exception e) {
	            throw new Exception(e.getMessage());
	        }
				
			
	}

	

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/listDueStudents", method = RequestMethod.GET)
	public ResponseEntity<DueListRes> listDueStudents( @RequestParam String id) throws Exception  {
		
		DueListRes res = new DueListRes();
		 try {
			 	
			 return dueService.listDueStudents(id);

	        } catch(NullPointerException e) {
				res.setMessage(new ResopnseCodes().notFoundMsg);
				res.setResponseCode(new ResopnseCodes().notFound);
				return ResponseEntity.status(HttpStatus.OK).body(res);
			}
		 catch (Exception e) {
	            throw new Exception(e.getMessage());
	        }
				
			
	}
}

