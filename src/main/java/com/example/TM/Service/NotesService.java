package com.example.TM.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.TM.Model.NotesModel;
import com.example.TM.Model.RegisterStudentModel;
import com.example.TM.Model.RegisterTutorModel;
import com.example.TM.ResDto.NotesDto;
import com.example.TM.ResDto.NotesRes;
import com.example.TM.Util.Encrypt;
import com.example.TM.Util.ResopnseCodes;

@Service
public class NotesService extends CentralService {
	
	/***
	 * <p>
	 * Stores the file <code>nFile</code> with specified <code>std</code>
	 * and <code>fileName</code> against particular <code>id</code>
	 * </p>
	 * @param id must not be {@literal null}.
	 * @param std must not be {@literal null}.
	 * @param fileName can be {@literal null}.
	 * @param nfile must not be {@literal null}.
	 * @return ResponseEntity
	 * @throws ApplicationException
	 */
	public ResponseEntity<NotesRes> UploadFile(String id,String std, String fileName, MultipartFile nfile) throws ApplicationException {
		try {
				NotesRes res= new NotesRes();
				HttpStatus httpSts = HttpStatus.OK;
				Long Tid=0L;
				List<NotesDto>  notesList=new ArrayList<NotesDto>();
				RegisterTutorModel user = registerTutorRepo.findOneByEmail(new Encrypt().decode(id));
				NotesModel notes = new NotesModel();
				if (user == null) {
				res.setMessage(new ResopnseCodes().notFoundMsg);
				res.setResponseCode(new ResopnseCodes().notFound);
				} else {
					Tid=user.getTid();
					long tmstmp = System.currentTimeMillis();
					if (nfile != null) {	
					String ext = FilenameUtils.getExtension(nfile.getOriginalFilename());
					String url = env.getProperty("NOTES_DIR") + user.getTid() +tmstmp+ "." + ext;
					File file = new File(env.getProperty("DCMNT_UPLOAD_PATH") + env.getProperty("NOTES_DIR")+ user.getTid() +tmstmp+ "." + ext);

					if (file.exists()) {
						file.delete();
					}
					nfile.transferTo(file);
			
					notes.setFileName(fileName);
					notes.setTid(Tid);
					notes.setUrl(url);
					notes.setStd(std);
					notesRepo.save(notes);
					
					notesList=getNotesList(Tid);
					NotesDto ndto= new NotesDto();
					ndto.setFileName(fileName);
					ndto.setStd(std);
					ndto.setUrl(url);
					ndto.setTid(Tid);
					notesList.add(ndto);
					
					res.setNotesList(notesList);
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
	 * List all the notes uploaded by the particular tutor
	 * registered with the Tid. 
	 * </p>
	 * @param Tid  must not be {@literal null}.
	 * @return List
	 */
	public List<NotesDto> getNotesList( Long Tid){
		List<NotesModel> prevnotes = notesRepo.findByTid(Tid);
		List<NotesDto>  notesList=new ArrayList<NotesDto>();
		for(NotesModel n: prevnotes) {
			NotesDto tmp= new NotesDto();
			tmp.setStd(n.getStd());
			tmp.setUrl(n.getUrl());
			tmp.setFileName(n.getFileName());
			tmp.setTid(n.getTid());
			notesList.add(tmp);
		}
		return notesList;
	}
	
	
	/***
	 * 
	 * @param id must not be {@literal null}.
	 * @return ResponseEntity
	 * @throws ApplicationException
	 */
	public ResponseEntity<NotesRes> getAllNotes(String id) throws ApplicationException {
		try {
				NotesRes res= new NotesRes();
				HttpStatus httpSts = HttpStatus.OK;
				
				Long Tid = registerTutorRepo.findOneByEmail(new Encrypt().decode(id)).getTid();
				
				res.setNotesList(getNotesList(Tid));			
				res.setMessage(new ResopnseCodes().okMsg);
			    res.setResponseCode(new ResopnseCodes().ok);
			    
				
				return ResponseEntity.status(httpSts).body(res);
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage(), null);
		}
		}
	
	
	/***
	 * <p>
	 * Lists all the notes the particular <code>id</code> is authorized to access.
	 * </p>
	 * <p>
	 * <b>Example : </b> Let there is a {@literal student} <code>A</code> , std 1 ,
	 * is registered with only <code>n</code> {@literal tutors} out of <code>K</code> {@literal tutors},
	 * total {@literal tutors} . The {@literal student} <code>A</code> must not be able to access
	 * the notes provided by the remaining i.e <code>(K-n)</code> {@literal tutors}.
	 * </p>
	 * @param id must not be {@literal null}
	 * @return ResponseEntity
	 * @throws ApplicationException
	 */
	public ResponseEntity<NotesRes> getNotesByStuId(String id) throws ApplicationException {
		try {
				NotesRes res= new NotesRes();
				HttpStatus httpSts = HttpStatus.OK;
				List<NotesDto>  notesList=new ArrayList<NotesDto>();
				List<NotesModel> tmp= new ArrayList<NotesModel>();
				
				List<RegisterStudentModel> stu =studentRepo.findByEmail(new Encrypt().decode(id)) ;
				for(RegisterStudentModel s: stu) {
					Long Tid= s.getTid();
					String std= s.getStd();
					tmp.addAll(notesRepo.findByTidAndStd(Tid,std));
				}
				for(NotesModel n: tmp) {
					NotesDto t= new NotesDto();
					t.setStd(n.getStd());
					t.setUrl(n.getUrl());
					t.setFileName(n.getFileName());
					t.setTid(n.getTid());
					notesList.add(t);	
				}			
				res.setMessage(new ResopnseCodes().okMsg);
			    res.setResponseCode(new ResopnseCodes().ok);
			    res.setNotesList(notesList);
			    
			    
				
				return ResponseEntity.status(httpSts).body(res);
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage(), null);
		}
		}
}
