package com.example.TM.Repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.TM.Model.ScheduleClassModel;


@Repository
public interface ScheduleClassRepo extends CrudRepository<ScheduleClassModel, Long>{

	List<ScheduleClassModel> findByTid(Long tid);

	List<ScheduleClassModel> findByStdAndTid(String std, Long tid);

	List<ScheduleClassModel> findByDate(String date);	

}
