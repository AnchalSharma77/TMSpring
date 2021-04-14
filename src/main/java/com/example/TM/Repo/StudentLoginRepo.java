package com.example.TM.Repo;

import org.springframework.data.repository.CrudRepository;

import com.example.TM.Model.StudentLoginModel;

public interface StudentLoginRepo extends CrudRepository<StudentLoginModel, Long> {

	StudentLoginModel findOneById(Long id);

	StudentLoginModel findOneByEmail(String id);

}
