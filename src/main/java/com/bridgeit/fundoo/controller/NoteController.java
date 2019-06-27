package com.bridgeit.fundoo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.fundoo.dto.Notedto;
import com.bridgeit.fundoo.model.Note;
import com.bridgeit.fundoo.responce.Responce;
import com.bridgeit.fundoo.service.INoteService;

@RestController
@RequestMapping("/note")
public class NoteController {

	@Autowired
	INoteService iNoteService;
	
	@PostMapping("/createnote")
	public Responce createNote(@RequestBody Notedto notedto,@RequestHeader String token)
	{
		Responce responce=iNoteService.createNote(notedto, token);
		return responce;
	}
	
	@PutMapping
	public Responce updateNote(@RequestBody Notedto notedto,@RequestHeader long noteid)
	{
		Responce responce=iNoteService.updateNote(notedto, noteid);
		return responce;
	}
	
	@GetMapping
	public List<Note> getAllNotes(@RequestHeader String token)
	{
		List<Note> notes=iNoteService.getAllNotes(token);
		return notes;
	}
	@DeleteMapping
	public Responce deleteNote(@RequestHeader String token,@RequestHeader long noteid)
	{
		Responce responce=iNoteService.deleteNote(token, noteid);
		return responce;
	}
	
	
}
