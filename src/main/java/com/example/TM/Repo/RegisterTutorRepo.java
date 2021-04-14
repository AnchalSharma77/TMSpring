package com.example.TM.Repo;

import org.springframework.data.repository.CrudRepository;

import com.example.TM.Model.RegisterTutorModel;

public interface RegisterTutorRepo extends CrudRepository<RegisterTutorModel ,Integer>{

	RegisterTutorModel findOneById(int mobile);
	//boolean existsById(String id);
	String findOneById(String id);
    RegisterTutorModel findOneByEmail(String email);
	RegisterTutorModel findOneByMobile(String mobile);
	boolean existsByEmail(String usrid);
	boolean existsByMobile(String usrid);

}
