package com.example.TM.Service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.TM.Model.DTSModel;
import com.example.TM.Model.DueModel;
import com.example.TM.Model.FeeModel;
import com.example.TM.Model.RegisterStudentModel;
import com.example.TM.ReqDto.AddStuReq;
import com.example.TM.Util.CurrDate;
import com.example.TM.Util.Encrypt;


@Service
public class RegisterStudentService extends CentralService{
		
	/***
	 * <p>
	 * Get the fee for the specified standard , <code>std</code> , for the particular <code>Tid</code>.
	 * </p>
	 * @param std must not be {@literal null}.
	 * @param Tid must not be {@literal null}.
	 * @return int
	 */
	public int getFee(int std ,Long Tid) {
		List<FeeModel> feeList= feeRepo.findByStd(std);
		for(FeeModel f : feeList) {
			if(Tid==f.getTid())
				return f.getFee();
		}
		//	return feeRepo.findOneByStd(std).getFee();
		return 0;
	}
	
	/***
	 * <p>
	 * Register the student with the details provided in <code>streq</code>
	 * with the tutor registered with <code>email</code> .
	 * </p>
	 * @param streq  must not be {@literal null}
	 * @param email  must not be {@literal null}
	 * @throws Exception
	 */
	public void addStudent( AddStuReq streq ,  String email) throws Exception  {
		int std = 0 ,  stuFee =0;
		RegisterStudentModel stu = new RegisterStudentModel();
		Long Tid = registerTutorRepo.findOneByEmail(new Encrypt().decode(email)).getTid();

		CurrDate dt = new CurrDate();
		stu.setName(streq.getName());
		stu.setMobile(streq.getMobile());
		stu.setEmail(streq.getEmail());
		stu.setStd(streq.getStd());
		stu.setDoj(dt.getCurrDate());
		stu.setTid(Tid);
		std=Integer.parseInt(streq.getStd());
		stuFee= studentService.getFee(std,Tid);
		if(stuFee==0) {
			throw new Exception("Fix Fee First");
		}
		stu.setFee(stuFee);
		studentRepo.save(stu);	
		
		
		DueModel due= new DueModel();
		due.setEmail(streq.getEmail());
		due.setMobile(streq.getMobile());
		due.setLd("");//means not made any payment yet
		due.setDm(1L);
		due.setSid(stu.getSid());
		due.setTid(Tid);
		Long fee=(long) stuFee;
		due.setDueFee(fee);
		dueRepo.save(due);
		
		
		DTSModel dts= new DTSModel();
		dts.setDid(due.getDid());
		dts.setTid(Tid);
		dts.setSid(stu.getSid());
		dtsRepo.save(dts);
	}

}
