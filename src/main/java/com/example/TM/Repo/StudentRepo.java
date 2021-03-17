package com.example.TM.Repo;

import org.springframework.data.repository.CrudRepository;

import com.example.TM.Model.StudentModel;

public interface StudentRepo extends CrudRepository<StudentModel,Long> {

}
