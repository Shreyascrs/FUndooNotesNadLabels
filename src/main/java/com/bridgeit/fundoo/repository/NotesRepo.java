package com.bridgeit.fundoo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bridgeit.fundoo.model.Note;

@Repository
public interface NotesRepo extends JpaRepository<Note, String> {

	Optional<Note> findByNoteid(long noteid);

	List<Note> findByUserid(String userid);

	Optional<Note> findByUseridAndNoteid(String userid, long noteid);
}
