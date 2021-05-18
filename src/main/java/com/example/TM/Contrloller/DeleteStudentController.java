package com.example.TM.Contrloller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TM.Model.DueModel;
import com.example.TM.ResDto.BaseResponse;
import com.example.TM.Service.CentralService;
import com.example.TM.Util.Encrypt;
import com.example.TM.Util.ResopnseCodes;

@RestController
@RequestMapping(value="/api",produces =" application/json")
public class DeleteStudentController extends CentralService {

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/deleteStudent")
	public ResponseEntity<BaseResponse> deleteStudennt(@RequestParam String id ,@RequestParam String tid) {
		DueModel dueData = new DueModel();
		BaseResponse res= new BaseResponse();
		boolean isEmail=registerTutorService.isEmail(id);
		Long tutorid=registerTutorRepo.findOneByEmail(new Encrypt().decode(tid)).getTid();
		try {
			dueData =dueRepo.findOneByEmailAndTid(id,tutorid);
		//dueData =isEmail? dueRepo.findOneByEmailAndTid(id,tutorid):dueRepo.findOneByMobile(id);
		Long dm = dueData.getDm();
		if(dm>0) {
			res.setResCode(new ResopnseCodes().invalid);
			res.setResMsg(new ResopnseCodes().invalidMsg);
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}
		else if(dm==0){
			if(isEmail) {
				studentRepo.delete(studentRepo.findOneByEmailAndTid(id,tutorid));
				dueRepo.delete(dueRepo.findOneByEmail(id));
			}
			else {
				studentRepo.delete(studentRepo.findOneByMobile(id));
				dueRepo.delete(dueRepo.findOneByMobile(id));
			}
			res.setResCode(new ResopnseCodes().ok);
			res.setResMsg(new ResopnseCodes().okMsg);
			return ResponseEntity.status(HttpStatus.OK).body(res);
			
		}
		}
		catch(Exception e){
			res.setResCode(new ResopnseCodes().notFound);
			res.setResMsg(new ResopnseCodes().notFoundMsg);
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}
		return null;
	}
	
}
