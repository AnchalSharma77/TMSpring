package com.example.TM.Service;



import java.io.File;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.TM.Model.RegisterStudentModel;
import com.example.TM.Model.RegisterTutorModel;
import com.example.TM.ResDto.PicUploadRes;
import com.example.TM.Util.Encrypt;
import com.example.TM.Util.ResopnseCodes;

@Service
public class ProfileImgService extends CentralService {

	/***
	 * <p>
	 * Add/Update the profile image ,<code>profileImg</code> ,
	 * for specified <code>id</code> (tutor)
	 * </p>
	 * @param id must not be {@literal null}.
	 * @param profileImg must not be {@literal null}.
	 * @return ResponseEntity
	 * @throws ApplicationException
	 */
	public ResponseEntity<PicUploadRes> saveTutorImage(String id, MultipartFile profileImg) throws ApplicationException {
		try {
					PicUploadRes res = new PicUploadRes();
		HttpStatus httpSts = HttpStatus.OK;
		RegisterTutorModel user = registerTutorRepo.findOneByEmail(new Encrypt().decode(id));

		if (user == null) {
			
		res.setMessage(new ResopnseCodes().notFoundMsg);
		res.setResponseCode(new ResopnseCodes().notFound);
		} else {
			
			if (profileImg != null) {
				long tmstmp = System.currentTimeMillis();
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
	
	
	/***
	 * <p>
	 * Gets the profile image for specified <code>id</code> (tutor)
	 * </p>
	 * @param id must not be {@literal null}.
	 * @return ResponseEntity
	 * @throws ApplicationException
	 */
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
			throw new ApplicationException(e.getMessage(), null);
		}

		
	}
	
	
	/***
	 * <p>
	 * Add/Update the profile image ,<code>profileImg</code> ,
	 * for specified <code>id</code> (student)
	 * </p>
	 * @param id must not be {@literal null}.
	 * @param profileImg must not be {@literal null}.
	 * @return ResponseEntity
	 * @throws Exception 
	 */
	public ResponseEntity<PicUploadRes> saveStudentImage(String id, MultipartFile profileImg) throws Exception {
		try {
				PicUploadRes res = new PicUploadRes();
				HttpStatus httpSts = HttpStatus.OK;
				List <RegisterStudentModel> users = studentRepo.findByEmail(new Encrypt().decode(id));
		
				if (users == null) { 
				res.setMessage(new ResopnseCodes().notFoundMsg);
				res.setResponseCode(new ResopnseCodes().notFound);
				} else {
					long tmstmp = System.currentTimeMillis();
					if (profileImg != null) {
//						for(RegisterStudentModel user:users) {
					RegisterStudentModel user =users.get(0);
					String ext = FilenameUtils.getExtension(profileImg.getOriginalFilename());
					String url = env.getProperty("STU_IMG_DIR") + user.getSid() +tmstmp+ "." + ext;
					File file = new File(env.getProperty("DCMNT_UPLOAD_PATH") + env.getProperty("STU_IMG_DIR")+ user.getSid() +tmstmp+ "." + ext);
					if (file.exists()) {
						file.delete();
					}
					profileImg.transferTo(file);
//					user.setImgUrl(url);
					res.setImgUrl(url);
//					studentRepo.save(user);
					for(RegisterStudentModel tmp :users) {
						tmp.setImgUrl(url);
						studentRepo.save(user);
					}
					}
					res.setMessage(new ResopnseCodes().okMsg);
		    		res.setResponseCode(new ResopnseCodes().ok);
				
				}
		
		
				return ResponseEntity.status(httpSts).body(res);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		}
	
	
	/***
	 * <p>
	 * Gets the profile image for specified <code>id</code> (student)
	 * </p>
	 * @param id must not be {@literal null}.
	 * @return ResponseEntity
	 * @throws Exception 
	 */
	public ResponseEntity<PicUploadRes> getStuImage(String id) throws Exception{
		PicUploadRes res = new PicUploadRes();
		HttpStatus httpSts = HttpStatus.OK;
		try {
		if (id == null) {
		res.setMessage(new ResopnseCodes().notFoundMsg);
		res.setResponseCode(new ResopnseCodes().notFound);
		} else {
			List <RegisterStudentModel> user = studentRepo.findByEmail(new Encrypt().decode(id));
			String url=user.get(0).getImgUrl();
			if(url==null) {
				res.setMessage(new ResopnseCodes().notFoundMsg);
				res.setResponseCode(new ResopnseCodes().notFound);
			}
			else {
			res.setImgUrl(url);
			}
			
		}
		return ResponseEntity.status(httpSts).body(res);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		
	}
	
	


}

