package com.example.TM.Repo;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.TM.Model.DueModel;

@Repository
public interface DueRepo extends CrudRepository<DueModel, Long>{

	    DueModel findOneByEmail(String email);
	    DueModel findOneByMobile(String mobile);
//		boolean existsByMobile(String usrid);
//		boolean existsByEmail(String usrid);
		DueModel findOneByDid(Long did);
		DueModel findOneBySid(Long sid);
		DueModel findOneByEmailAndTid(String id, Long tutorid);
		boolean existsByEmailAndTid(String sid, Long tid);
	//	boolean existsOneByEmailAndTid(String sid, Long tid);
		boolean existsDueModelByEmailAndTid(String sid, Long tid);
		List<DueModel> findByTid(Long tid);
		
		

}
