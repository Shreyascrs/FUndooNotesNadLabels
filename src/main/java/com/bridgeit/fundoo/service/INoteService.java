package com.bridgeit.fundoo.service;

import java.util.List;

import com.bridgeit.fundoo.dto.Notedto;
import com.bridgeit.fundoo.model.Note;
import com.bridgeit.fundoo.responce.Responce;

public interface INoteService {

	public Responce createNote(Notedto notedto,String token);
	public Responce updateNote(Notedto notedto,long noteid);
	public List<Note> getAllNotes(String token);
	public Responce deleteNote(String token,long noteid);
}
