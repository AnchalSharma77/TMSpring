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
	            return profileImgService.saveTutorImage(id, profileImg);

	        } catch (Exception e) {
	            throw new Exception(e.getMessage());
	        }			
	}
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getProfileImg", method = RequestMethod.GET)
	public ResponseEntity<PicUploadRes> getProfileImage( @RequestParam("id") String id) throws Exception  {		
		 try {
			 return profileImgService.getTutorImage(id);

	        } catch (Exception e) {
	            throw new Exception(e.getMessage());
	        }
	}


	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/updateStuProfileImg", method = RequestMethod.POST)
	public ResponseEntity<PicUploadRes> uploadStuProfileImages(@RequestParam(value = "profileImg", required = false) MultipartFile profileImg,
            @RequestParam("id") String id) throws Exception  {
		 try {
			 System.out.println("Api");
	            return profileImgService.saveStudentImage(id, profileImg);

	        } catch (Exception e) {
	            throw new Exception(e.getMessage());
	        }			
	}
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getStuProfileImg", method = RequestMethod.GET)
	public ResponseEntity<PicUploadRes> getStuProfileImage( @RequestParam("id") String id) throws Exception  {		
		 try {
			 return profileImgService.getStuImage(id);

	        } catch (Exception e) {
	            throw new Exception(e.getMessage());
	        }
	}

}
