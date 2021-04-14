package com.example.TM.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.TM.Model.DueModel;

@Repository
public interface DueRepo extends CrudRepository<DueModel, Long>{

	    DueModel findOneByEmail(String email);
	    DueModel findOneByMobile(String mobile);
		boolean existsByMobile(String usrid);
		boolean existsByEmail(String usrid);
		//DueModel findById(String id);
		

}
