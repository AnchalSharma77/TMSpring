package com.example.TM.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.TM.Model.RegisterTutorModel;
import com.example.TM.ReqDto.EditDescReq;
import com.example.TM.ResDto.DescRes;
import com.example.TM.ResDto.EditDescRes;
import com.example.TM.Util.Encrypt;
import com.example.TM.Util.ResopnseCodes;


@Service
public class EditDescService extends CentralService {

	public ResponseEntity<EditDescRes> editAbout(String id, EditDescReq about) throws Exception {
		
		EditDescRes res= new EditDescRes();
		try {
			String abt= about.getDesc();
		if(id!=null) {
			RegisterTutorModel tutor = registerTutorRepo.findOneByEmail(new Encrypt().decode(id));
			tutor.setDescription(abt);
			registerTutorRepo.save(tutor);
			res.setAbout(abt);
			res.setResCode(new ResopnseCodes().ok);
			res.setResMsg(new ResopnseCodes().okMsg);
			return ResponseEntity.status(HttpStatus.OK).body(res);
			}
		else {
			res.setResCode(new ResopnseCodes().notFound);
			res.setResMsg(new ResopnseCodes().notFoundMsg);
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}
		}
		catch (Exception e) {
			
			System.out.println(e);
			throw new Exception(e.getMessage());
		}
	}
	
	public ResponseEntity<DescRes> getAbout(String id) throws Exception {
		
		DescRes res= new DescRes();
		try {
		if(id!=null) {
			RegisterTutorModel tutor = registerTutorRepo.findOneByEmail(new Encrypt().decode(id));
			String desc= tutor.getDescription();
			res.setDesc(desc);
			res.setResCode(new ResopnseCodes().ok);
			res.setResMsg(new ResopnseCodes().okMsg);
			return ResponseEntity.status(HttpStatus.OK).body(res);
			}
		else {
			res.setResCode(new ResopnseCodes().notFound);
			res.setResMsg(new ResopnseCodes().notFoundMsg);
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}
		}
		catch (Exception e) {
			
			System.out.println(e);
			throw new Exception(e.getMessage());
		}
	}

}
