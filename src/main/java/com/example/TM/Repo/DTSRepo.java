package com.example.TM.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.TM.Model.DTSModel;

@Repository
public interface DTSRepo extends CrudRepository<DTSModel, Long>{

}
