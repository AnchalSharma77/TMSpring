package com.example.TM.Service;

import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.portable.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.TM.Model.DueModel;
import com.example.TM.Model.FeeModel;
import com.example.TM.Model.RegisterStudentModel;
import com.example.TM.ResDto.DueDto;
import com.example.TM.ResDto.DueListRes;
import com.example.TM.ResDto.DueRes;

import com.example.TM.Util.CurrDate;
import com.example.TM.Util.Encrypt;
import com.example.TM.Util.ResopnseCodes;

@Service
public class DueService extends CentralService {
	
	CurrDate currDate = new CurrDate();

	/***
	 * Checks whether the particular {@code sid} is registered 
	 * with the particular {@code tid}
	 * @param sid student's unique id,must not be {@literal null}.
	 * @param tid tutors's unique id, must not be {@literal null}.
	 * @return {@code true} if the id is registered , {@code false} otherwise
	 */
	public boolean studentExist(String sid , Long tid) {
		return dueRepo.existsDueModelByEmailAndTid(sid,tid);
	}
	/***
	 * Gets standard corresponding to specific userId {@code id}
	 * @param id must not be {@literal null}.
	 * @return  {@literal String}  
	 */
	public String getStd(String id) {
		Long mobile= Long.parseLong(id);
		RegisterStudentModel stud=studentRepo.findOneByMobile(mobile);
		return stud.getStd();
	}
	/***
	 * Return the last Date on which fee was paid , for given id
	 * @param id must not be {@literal null}.
	 * @return {@literal String} 
	 */
	public String getLastDate(String id) {
		boolean isEmail=registerTutorService.isEmail(id);
		String lastDate=isEmail?dueRepo.findOneByEmail(id).getLd():dueRepo.findOneByMobile(id).getLd();
		
		return lastDate;
	}
	/***
	 * total due fee of the student registered with {@literal id}
	 * @param id must not be {@literal null}
	 * @return Long
	 */
	public Long getDueById(String id) {
		
		return registerTutorService.isEmail(id)?dueRepo.findOneByEmail(id).getDueFee():dueRepo.findOneByMobile(id).getDueFee();
	}
	
	/***
	 * 
	 * Returns all instances of the type {@code DueModel} with the given IDs.
	 * <p>
	 * If some or all ids are not found, no entities are returned for these IDs.
	 * <p>
	 * Note that the order of elements in the result is not guaranteed
	 * 
	 * @return {@literal List<DueModel>}
	 */
	public List<DueModel> getAll(){
		return (List<DueModel>) dueRepo.findAll();
	}

	/***
	 * get the sum of due fee of all the students
	 * @param id must not be {@literal null}
	 * @return {@literal Long}
	 */
	public Long getDuesSum(String id) {
		Long sum = 0L;
		Long Tid=registerTutorRepo.findOneByEmail(new Encrypt().decode(id)).getTid();
		List<DueModel> dmodel= getAll();
		if(!(dmodel.equals(null))) {
			
			for(DueModel stu : dmodel) {
				if(Tid==stu.getTid())
				sum= sum+ stu.getDueFee();
			}
		}
		return sum;
	}
	
	

	
	/***
	 *  <p>Find due months and due fee for 
	 *  particular sEmail and tEmail combination
	 *  </p>
	 *  <p>
	 *  sEmail is the email id (with which
	 *  the student is registered) and tEmail is
	 *  the user id of tutor , which is used
	 *  to get the unique {@literal Tid} .
	 *  </p>
	 *  
	 * @param sEmail must not be {@literal null}
	 * @param tEmail must not be {@literal null}
	 * @return ResponseEntity
	 * @throws ApplicationException
	 */

	public ResponseEntity<DueRes> getDueById(String sEmail , String tEmail) throws ApplicationException {
		try {
					DueRes res = new DueRes();
					HttpStatus httpSts = HttpStatus.OK;
					Long dueFee=0L;
					String std;
					List<FeeModel> fees;
					Long tutorid=registerTutorRepo.findOneByEmail(new Encrypt().decode(tEmail)).getTid();
					if(studentExist(sEmail,tutorid)) {
						std=studentRepo.findOneByEmailAndTid(sEmail,tutorid).getStd();
					int st=Integer.parseInt(std);
					fees=feeRepo.findByTid(tutorid);
					for(FeeModel f :fees) {
					if(f.getStd()==st) {
					int monthFee=f.getFee();
					Long duration = dueRepo.findOneByEmailAndTid(sEmail,tutorid).getDm();
					
					dueFee=duration*monthFee;
					
					res.setMessage(new ResopnseCodes().okMsg);
			        res.setResponseCode(new ResopnseCodes().ok);
			        res.setDm(duration);
			        res.setDueFee(dueFee);
			        
					}}
					}

					else {
			
					res.setMessage(new ResopnseCodes().notFoundMsg);
					res.setResponseCode(new ResopnseCodes().notFound);
					} 


			return ResponseEntity.status(httpSts).body(res);
			} catch (Exception e) {
				throw new ApplicationException(e.getMessage(), null);
			}
		}
	
	

	/***
	 * <p>
	 * List due record of all the students registered
	 * with particular temail.
	 * </p>
	 * @param temail must not be {@literal null}
	 * @return List<DueDto>
	 */

	public List<DueDto> getlistDueStudents (String temail) {
		Long Tid=registerTutorRepo.findOneByEmail(temail).getTid();
		List<DueDto> dueList= new ArrayList<DueDto>();
		List<DueModel> allDueList = dueRepo.findByTid(Tid);
		for(DueModel d : allDueList) {
			
				DueDto dto = new DueDto();
				dto.setDm(d.getDm());
				dto.setDueFee(d.getDueFee());
				dto.setLd(d.getLd());
				dto.setEmail(d.getEmail());
				dto.setMobile(d.getMobile());
				dueList.add(dto);
				
		}
		return dueList;
	}
	
	/***
	 * <p>
	 * Set response for the dueList request .
	 * </p>
	 * @param tid must not be {@literal null}
	 * @return ResponseEntity <DueListRes>
	 * @throws ApplicationException
	 */
	
	public ResponseEntity<DueListRes> listDueStudents( String tid) throws ApplicationException {
		DueListRes res = new DueListRes();
		try {
					List<DueDto> ls = new ArrayList<DueDto>();
					HttpStatus httpSts = HttpStatus.OK;
					String tEmail=new Encrypt().decode(tid);
					
					if(registerTutorRepo.existsByEmail(tEmail)) {
	
						ls=getlistDueStudents(tEmail);
						
						res.setMessage(new ResopnseCodes().okMsg);
				        res.setResponseCode(new ResopnseCodes().ok);
			            res.setDueList(ls);
					}

					else {
			
						res.setMessage(new ResopnseCodes().notFoundMsg);
						res.setResponseCode(new ResopnseCodes().notFound);
					} 

				return ResponseEntity.status(httpSts).body(res);
				}
		catch (Exception e) {
					throw new ApplicationException(e.getMessage(), null);
				}
		}
	


	
}











