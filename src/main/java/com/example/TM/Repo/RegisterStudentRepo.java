package com.example.TM.Repo;

import org.springframework.data.repository.CrudRepository;

import com.example.TM.Model.RegisterStudentModel;

public interface RegisterStudentRepo extends CrudRepository<RegisterStudentModel,Long> {

	//RegisterStudentModel findOneByMobile(Long mobile);

	RegisterStudentModel findOneByMobile(Long mobile);

	RegisterStudentModel findOneByMobile(String id);

	RegisterStudentModel findOneByEmail(String id);

	boolean existsByEmail(String id);

	boolean existsByMobile(String id);

}
