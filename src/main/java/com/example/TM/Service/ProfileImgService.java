package com.example.TM.Service;



import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.TM.Model.RegisterTutorModel;
import com.example.TM.ResDto.PicUploadRes;
import com.example.TM.Util.Encrypt;
import com.example.TM.Util.ResopnseCodes;

@Service
public class ProfileImgService extends CentralService {

	@Autowired
	public Environment env;
	
	public ResponseEntity<PicUploadRes> saveUserImage(String id, MultipartFile profileImg) throws ApplicationException {
		try {
					PicUploadRes res = new PicUploadRes();
		HttpStatus httpSts = HttpStatus.OK;

		//LOGGER.info("Uploading Profile Image of User::"+id);
		RegisterTutorModel user = registerTutorRepo.findOneByEmail(new Encrypt().decode(id));

		if (user == null) {
			
		res.setMessage(new ResopnseCodes().notFoundMsg);
		res.setResponseCode(new ResopnseCodes().notFound);
		} else {
			long tmstmp = System.currentTimeMillis();
			if (profileImg != null) {
				
			String ext = FilenameUtils.getExtension(profileImg.getOriginalFilename());
			String url = env.getProperty("TUTOR_IMG_DIR") + user.getTid() +tmstmp+ "." + ext;
	
	
			File file = new File(env.getProperty("DCMNT_UPLOAD_PATH") + env.getProperty("TUTOR_IMG_DIR")+ user.getTid() +tmstmp+ "." + ext);
			if (file.exists()) {
				file.delete();
			}
			profileImg.transferTo(file);
	
			user.setImgUrl(url);
			res.setImgUrl(url);
			
			}
			
					registerTutorRepo.save(user);
					res.setMessage(new ResopnseCodes().okMsg);
			        res.setResponseCode(new ResopnseCodes().ok);
		}


		return ResponseEntity.status(httpSts).body(res);
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage(), null);
		}
		}
	
	
	
	public ResponseEntity<PicUploadRes> getTutorImage(String id) throws ApplicationException{
		PicUploadRes res = new PicUploadRes();
		HttpStatus httpSts = HttpStatus.OK;
		try {

		if (id == null) {
		res.setMessage(new ResopnseCodes().notFoundMsg);
		res.setResponseCode(new ResopnseCodes().notFound);
		} else {
			RegisterTutorModel user = registerTutorRepo.findOneByEmail(new Encrypt().decode(id));
			res.setImgUrl(user.getImgUrl());
			
		}
		return ResponseEntity.status(httpSts).body(res);
		}catch (Exception e) {
			//LOGGER.info(e.getMessage());
			throw new ApplicationException(e.getMessage(), null);
		}

		
	}

}

//
//public boolean updateProfileImg(ProfileImgDto pi )
//{
//	String eid=pi.getId();
//	ProfileImgModel imgData = new ProfileImgModel();
//	if(studentRepo.existsByEmail(eid)|| registerTutorRepo.existsByEmail(eid)) {
//		imgData.setId(eid);
//		imgData.setImgUrl(pi.getImgUrl());
//		profileImgRepo.save(imgData);
//		return true;
//	}
//	
//	
//	return false;
//}