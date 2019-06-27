package com.bridgeit.fundoo.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgeit.fundoo.dto.Notedto;
import com.bridgeit.fundoo.model.Note;
import com.bridgeit.fundoo.repository.NotesRepo;
import com.bridgeit.fundoo.responce.Responce;
import com.bridgeit.fundoo.utility.TimeUtility;
import com.bridgeit.fundoo.utility.TokenUtility;

@Service
public class NoteServiceImpl implements INoteService {

	@Autowired
	TokenUtility tokenUtility;
	@Autowired
	NotesRepo repository;
	@Autowired
	ModelMapper mapper;
	@Autowired
	Responce responce;

	public Responce createNote(Notedto notedto, String token) {
		Note note = mapper.map(notedto, Note.class);
		String userid = tokenUtility.verifyToken(token);
		note.setUserid(userid);
		note.setCreatedTime(TimeUtility.todayDate());
		note.setUpdatedTime(TimeUtility.todayDate());
		note.setColor("white");
		note.setPin(false);
		note.setArchive(false);
		note.setTrash(false);

		try {
			repository.save(note);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return responce.sendResponse(200, "note is saved", "");
	}

	@Override
	public Responce updateNote(Notedto notedto, long noteid) {
		Optional<Note> updatednote = repository.findByNoteid(noteid);
		if (updatednote.isPresent()) {

			updatednote.get().setTitle(notedto.getTitle());
			updatednote.get().setDescription(notedto.getDescription());
			updatednote.get().setUpdatedTime(TimeUtility.todayDate());
		}
		try {
			Note updated = repository.save(updatednote.get());
			updated.toString();

		} catch (Exception e) {
			e.printStackTrace();
			responce.sendResponse(400, "note not updated", "");
		}
		return responce.sendResponse(HttpStatus.ACCEPTED.value(), "note updated successfully", "");

	}

	@Override
	public List<Note> getAllNotes(String token) {
		String userid = tokenUtility.verifyToken(token);
		List<Note> notes = repository.findByUserid(userid);
		return notes;
	}

	@Override
	public Responce deleteNote(String token, long noteid) {
		String userid = tokenUtility.verifyToken(token);
		Optional<Note> note = repository.findByUseridAndNoteid(userid, noteid);
		if (note.isPresent()) {
			repository.delete(note.get());
			return responce.sendResponse(200, "note deleted successfully", "");
		}
		return responce.sendResponse(400, "note not found", "");
	}

	public List<Note> sortOnCreatedTime(String token) {
		String userid = tokenUtility.verifyToken(token);
		List<Note> notes = repository.findByUserid(userid);
		return null;
	}

}
