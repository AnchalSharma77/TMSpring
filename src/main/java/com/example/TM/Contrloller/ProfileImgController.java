package com.example.TM.Contrloller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.TM.ResDto.PicUploadRes;
import com.example.TM.Service.CentralService;


@RestController
@RequestMapping(value="/api",produces ="application/json")

public class ProfileImgController extends CentralService {
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/updateProfileImg", method = RequestMethod.POST)
	public ResponseEntity<PicUploadRes> uploadProfileImages(@RequestParam(value = "profileImg", required = false) MultipartFile profileImg,
            @RequestParam("id") String id) throws Exception  {
		 try {
	            return profileImgService.saveUserImage(id, profileImg);

	        } catch (Exception e) {
	          //  LOGGER.error(e.getMessage());
	            throw new Exception(e.getMessage());
	        }			
	}
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getProfileImg", method = RequestMethod.GET)
	public ResponseEntity<PicUploadRes> getProfileImage( @RequestParam("id") String id) throws Exception  {
		
		 try {
			 	
	            return profileImgService.getTutorImage(id);

	        } catch (Exception e) {
	          //  LOGGER.error(e.getMessage());
	            throw new Exception(e.getMessage());
	        }
				
			
	}


	
//	public ResponseEntity<BaseResponse> updateProfileImg(@RequestBody ProfileImgDto pi ) {
//		
//		BaseResponse res= new BaseResponse();
//		try {
//		if(profileImgService.updateProfileImg(pi)) {
//			res.setResCode(new ResopnseCodes().ok);
//			res.setResMsg(new ResopnseCodes().okMsg);
//			return ResponseEntity.status(HttpStatus.OK).body(res);
//		}
//		else {
//			res.setResCode(new ResopnseCodes().notFound);
//			res.setResMsg(new ResopnseCodes().notFoundMsg);
//			return ResponseEntity.status(HttpStatus.OK).body(res);
//		}
//		}
//		catch(Exception e) {
//		res.setResCode(new ResopnseCodes().invalid);
//		res.setResMsg(new ResopnseCodes().invalidMsg);
//		return ResponseEntity.status(HttpStatus.OK).body(res);
//		}
//		
//			
//	}
	

}
