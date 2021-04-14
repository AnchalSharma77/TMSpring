package com.example.TM.Repo;

import org.springframework.data.repository.CrudRepository;

import com.example.TM.Model.AttendanceModel;

public interface AttendanceRepo extends CrudRepository<AttendanceModel, Long> {

}
