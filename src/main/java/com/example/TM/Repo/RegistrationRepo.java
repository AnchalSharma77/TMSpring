package com.example.TM.Repo;

import org.springframework.data.repository.CrudRepository;

import com.example.TM.Model.RegisterModel;

public interface RegistrationRepo extends CrudRepository<RegisterModel ,Integer>{

	RegisterModel findOneById(int mobile);
	//boolean existsById(String id);
	String findOneById(String id);
    RegisterModel findOneByEmail(String email);
	RegisterModel findOneByMobile(String mobile);
	boolean existsByEmail(String usrid);
	boolean existsByMobile(String usrid);

}
