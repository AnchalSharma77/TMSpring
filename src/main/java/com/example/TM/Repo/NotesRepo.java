package com.example.TM.Repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.TM.Model.NotesModel;

@Repository
public interface NotesRepo extends CrudRepository<NotesModel, Long>{

	List<NotesModel> findByTid(Long tid);

	List<NotesModel> findByTidAndStd(Long tid, String std);

}
