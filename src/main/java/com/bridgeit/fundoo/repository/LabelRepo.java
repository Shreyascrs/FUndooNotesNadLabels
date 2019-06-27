package com.bridgeit.fundoo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgeit.fundoo.model.Label;



public interface LabelRepo extends JpaRepository<Label, Long> {
	Optional<Label> findBylabelId(long labelId);
	Optional<Label> findByUserIdAndLabelId(String uid,long lid);
	List<Label> findByUserId(String userid);
	
}
