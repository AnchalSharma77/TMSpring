package com.example.TM.Repo;

import org.springframework.data.repository.CrudRepository;

import com.example.TM.Model.FeeModel;

public interface FeeRepo extends CrudRepository <FeeModel , Integer> {

	FeeModel findOneById(int id);

}
