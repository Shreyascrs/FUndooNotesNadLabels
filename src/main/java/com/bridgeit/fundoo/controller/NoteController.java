package com.bridgeit.fundoo.controller;

import java.io.UnsupportedEncodingException;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.bridgeit.fundoo.dto.Notedto;
import com.bridgeit.fundoo.model.Note;
import com.bridgeit.fundoo.responce.Responce;
import com.bridgeit.fundoo.service.INoteService;

@RestController
@RequestMapping("/note")
public class NoteController {

	@Autowired
	INoteService iNoteService;
	
	@Autowired
	RestTemplate resttemplate;

	@PostMapping("/createnote")
	public Responce createNote(@RequestBody Notedto notedto, @RequestHeader String token) {
		RestTemplate restTemplate = new RestTemplate();

		boolean ispresent = restTemplate.getForObject("http://localhost:9090/user/userPresent/" + token, Boolean.class);
		if (ispresent) {

			Responce responce = iNoteService.createNote(notedto, token);
			return responce;
		} else {
			Responce responce = new Responce();
			responce.sendResponse(400, "user not present", "");
			return responce;
		}
	}

	@PutMapping
	public Responce updateNote(@RequestBody @Valid Notedto notedto, @RequestHeader long noteid) {
		Responce responce = iNoteService.updateNote(notedto, noteid);
		return responce;
	}

	@GetMapping
	public List<Note> getAllNotes(@RequestHeader String token) {
		List<Note> notes = iNoteService.getAllNotes(token);
		return notes;
	}

	@DeleteMapping
	public Responce deleteNote(@RequestHeader String token, @RequestHeader long noteid) {
		Responce responce = iNoteService.deleteNote(token, noteid);
		return responce;
	}

	@GetMapping("/addlabel")
	public Responce addlabel(@RequestHeader String token, @RequestHeader long noteid, @RequestHeader long labelid) {
		RestTemplate restTemplate1 = new RestTemplate();
		boolean ispresent = restTemplate1.getForObject("http://localhost:9090/user/userPresent/" + token,
				Boolean.class);
		if (ispresent) {
			Responce resp = iNoteService.addLabelToNote(token, noteid, labelid);
			return resp;
		} else {
			Responce responce = new Responce();
			return responce.sendResponse(400, "user not found", "");
		}
	}

	@GetMapping("/removelabel")
	public Responce removelabelfromnote(@RequestHeader String token, @RequestHeader long noteid,
			@RequestHeader long labelid) {
		Responce resp = iNoteService.deleteLabelFromNote(token, noteid, labelid);
		return resp;
	}

	@GetMapping("/redis")
	public Note getredisNote(@RequestHeader long noteid) throws IllegalArgumentException, UnsupportedEncodingException {
		return iNoteService.getallRedis(noteid);
	}

	@GetMapping("/redisallnotes")
	public Note getAllRedisNotes(@RequestHeader String key) {
		return iNoteService.getValue(key);
	}
}
