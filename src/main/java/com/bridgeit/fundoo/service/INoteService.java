package com.bridgeit.fundoo.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.bridgeit.fundoo.dto.Notedto;
import com.bridgeit.fundoo.model.Note;
import com.bridgeit.fundoo.responce.Responce;

public interface INoteService {

	public Responce createNote(Notedto notedto,String token);

	public Responce updateNote(Notedto notedto,long noteid);
	
	public List<Note> getAllNotes(String token);
	
	public Responce deleteNote(String token,long noteid);
	
	public Responce ispin(String token,long noteid);
	
	public Responce isarchive(String token,long noteid);
	
	public Responce istrash(String token,long noteid);
	
	public Responce addLabelToNote(String token,long noteid,long labelid);
	
	public Responce deleteLabelFromNote(String token,long noteid,long labelid);
	
	public Note getallRedis(long id) throws IllegalArgumentException, UnsupportedEncodingException;
	
	public Note getValue(String key);
        
     
}
