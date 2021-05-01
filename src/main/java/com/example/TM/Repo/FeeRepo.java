package com.example.TM.Repo;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.TM.Model.FeeModel;

public interface FeeRepo extends CrudRepository <FeeModel , Integer> {

	FeeModel findOneByStd(int l);

	List<FeeModel> findByStd(int std);

	List<FeeModel> findByTid(Long tid);

	boolean existsOneByStdAndTid(int std, Long tid);

	FeeModel findOneByStdAndTid(int std, Long tid);

//	FeeModel findOneById(String std);

}
