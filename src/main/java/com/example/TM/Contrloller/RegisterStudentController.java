package com.example.TM.Contrloller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TM.Model.RegisterStudentModel;
import com.example.TM.ReqDto.AddStuReq;
import com.example.TM.ReqDto.BasicStuData;
import com.example.TM.ResDto.BaseResponse;
import com.example.TM.Service.CentralService;
import com.example.TM.Util.Encrypt;
import com.example.TM.Util.ResopnseCodes;


@RestController
@RequestMapping(value="/api",produces =" application/json")
public class RegisterStudentController extends CentralService {
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getStudent")
	public RegisterStudentModel getStudent(@RequestParam String id) {
		Long mobile= Long.parseLong(id);
		return studentRepo.findOneByMobile(mobile);
	}
	
	@GetMapping("/allStu")
	public List<RegisterStudentModel> getAllStu(){
		return (List<RegisterStudentModel>) studentRepo.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getStudentsCount")
	public long getStudentsCount() {
		return studentRepo.count();
	}
	
	/**
	 * 
	 * @param Tid
	 * @return count of students registered with particular tutor
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getTotalStudents")
	public long getTotalStudents(@RequestParam String id) {
		try {
		Long Tid=registerTutorRepo.findOneByEmail(new Encrypt().decode(id)).getTid();
		List<RegisterStudentModel> students =(List<RegisterStudentModel>)studentRepo.findByTid(Tid);
		return (long) students.size();
		}catch(NullPointerException e) {
			return 0;
		}
	}
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getBasicStudData")
	public List<BasicStuData>  basicStuData(@RequestParam String id) {
		
		List<BasicStuData> stuList = new ArrayList<BasicStuData>();
		List<RegisterStudentModel> allStu=getAllStu();
		Long Tid= registerTutorRepo.findOneByEmail(new Encrypt().decode(id)).getTid();
		for(RegisterStudentModel st:allStu) {
			if(Tid==st.getTid()) {
			BasicStuData stu= new BasicStuData();
			stu.setEmail(st.getEmail());
			stu.setMobile(st.getMobile());
			stu.setName(st.getName());
			stuList.add(stu);
			}
		}
		
		return stuList;
		
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/registerStudent")
	public ResponseEntity<BaseResponse> addStudent(@RequestBody AddStuReq streq , @RequestParam String id) {
		BaseResponse res= new BaseResponse();
	
		try {
			
			return registerStudentService.addStudent(streq ,id);
			
			
		}catch(Exception e) {
			res.setResCode(new ResopnseCodes().invalid);
			res.setResMsg(new ResopnseCodes().invalidMsg);
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}
	}
	
	
}
