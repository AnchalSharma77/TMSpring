package com.example.TM.Repo;

import org.springframework.data.repository.CrudRepository;

import com.example.TM.Model.StudentLoginModel;

public interface StudentLoginRepo extends CrudRepository<StudentLoginModel, String> {

	//StudentLoginModel findOneById(Long id);

	//StudentLoginModel findOneByEmail(String id);

	StudentLoginModel findOneById(String id);

}
