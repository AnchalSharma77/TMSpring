package com.example.TM.Repo;

import org.springframework.data.repository.CrudRepository;

import com.example.TM.Model.AttendanceModel;

public interface AttendanceRepo extends CrudRepository<AttendanceModel, Long> {

	AttendanceModel findOneByTidAndDateAndEmail(Long tid, String currDate, String semail);

	boolean existsOneByEmailAndDateAndTid(String email, String date, Long tid);

	AttendanceModel findOneByEmailAndDateAndTid(String email, String date, Long tid);

}
