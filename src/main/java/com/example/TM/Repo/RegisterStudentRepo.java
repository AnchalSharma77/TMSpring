package com.example.TM.Repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.TM.Model.FeeModel;
import com.example.TM.Model.RegisterStudentModel;

public interface RegisterStudentRepo extends CrudRepository<RegisterStudentModel,Long> {

	//RegisterStudentModel findOneByMobile(Long mobile);

	RegisterStudentModel findOneByMobile(Long mobile);

	RegisterStudentModel findOneByMobile(String id);

	RegisterStudentModel findOneByEmail(String id);

	boolean existsByEmail(String id);

	boolean existsByMobile(String id);

	void deleteOneByEmail(String id);

	void deleteOneByMobile(String id);

	List<RegisterStudentModel> findByTid(Long Tid);

	List<RegisterStudentModel> findByEmail(String semail);

	RegisterStudentModel findOneByMobileAndTid(Long mobile, Long tid);

	FeeModel findOneByEmailAndTid(String sid, String tid);

	RegisterStudentModel findOneByEmailAndTid(String sid, Long tid);

}
