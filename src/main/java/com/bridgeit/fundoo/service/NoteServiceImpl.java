package com.bridgeit.fundoo.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgeit.fundoo.dto.Notedto;
import com.bridgeit.fundoo.elasticsearch.IElasticSearch;
import com.bridgeit.fundoo.model.Label;
import com.bridgeit.fundoo.model.Note;
import com.bridgeit.fundoo.redis.RedisService;
import com.bridgeit.fundoo.repository.LabelRepo;
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
	@Autowired
	LabelRepo labelrepository;
	@Autowired
	IElasticSearch elasticsearch;
	@Autowired
	RedisService<Note> redis;
	
	private static final Logger log = LoggerFactory.getLogger(NoteServiceImpl.class);

	public Responce createNote(Notedto notedto, String token) {

		String userid = tokenUtility.verifyToken(token);
		Note note = mapper.map(notedto, Note.class);
//		Note note = new Note();
//		BeanUtils.copyProperties(notedto, note);
//		log.info(note.toString());
		note.setUserid(userid);
		note.setCreatedTime(TimeUtility.todayDate());
		note.setUpdatedTime(TimeUtility.todayDate());
		note.setColor("white");
		note.setPin(false);
		note.setArchive(false);
		note.setTrash(false);
		System.out.println("entered for testing");
		Note noter;

		try {
			noter = repository.save(note);
			// redis.putMap("notes", note.getNoteid(), noter);
//			elasticsearch.createNote(note);

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

	@Override
	public Responce ispin(String token, long noteid) {
		String userid = tokenUtility.verifyToken(token);
		Optional<Note> isnote = repository.findByUseridAndNoteid(userid, noteid);
		if (isnote.isPresent()) {
			Note note = isnote.get();
			if (note.isTrash()) {
				return responce.sendResponse(400, "note is in trash", "");
			}
			if (note.isPin()) {
				note.setPin(false);
			} else {
				note.setPin(true);
			}
		} else {
			return responce.sendResponse(400, "note not present", "");
		}
		return responce.sendResponse(200, "pin data successfull", "");
	}

	@Override
	public Responce isarchive(String token, long noteid) {
		String userid = tokenUtility.verifyToken(token);
		Optional<Note> isnote = repository.findByUseridAndNoteid(userid, noteid);
		if (isnote.isPresent()) {
			Note note = isnote.get();
			if (note.isPin()) {
				note.setPin(false);
				repository.save(note);
			}
			if (note.isArchive()) {
				note.setArchive(false);
			} else {
				note.setArchive(true);
			}
		} else {
			return responce.sendResponse(400, "note not present", "");
		}
		return responce.sendResponse(200, "archive successfull", "");
	}

	@Override
	public Responce istrash(String token, long noteid) {
		String userid = tokenUtility.verifyToken(token);
		Optional<Note> isnote = repository.findByUseridAndNoteid(userid, noteid);
		if (isnote.isPresent()) {
			Note note = isnote.get();
			if (note.isPin()) {
				note.setPin(false);
				repository.save(note);
			} else if (note.isArchive()) {
				note.setArchive(false);
				repository.save(note);
			}
			note.setTrash(true);

		} else {
			responce.sendResponse(400, "note not present", "");
		}
		return responce.sendResponse(200, "note trashed", "");
	}

	@Override
	public Responce addLabelToNote(String token, long noteid, long labelid) {
		String userid = tokenUtility.verifyToken(token);
		Optional<Note> isnote = repository.findByUseridAndNoteid(userid, noteid);
		Optional<Label> islabel = labelrepository.findByUserIdAndLabelId(userid, labelid);
		if (isnote.isPresent() && islabel.isPresent()) {
			Note note = isnote.get();
			Label label = islabel.get();
			if (!note.isTrash()) {
				note.setUpdatedTime(TimeUtility.todayDate());
				List<Label> islabels = note.getLabels();
				System.out.println(islabels);
				if (islabels != null) {
					Optional<Label> oplabel = islabels.stream().filter(l -> l.getLabelId() == label.getLabelId())
							.findFirst();
					if (oplabel.isPresent()) {
						islabels.add(label);
						note.setLabels(islabels);
						repository.save(note);
						return responce.sendResponse(200, "label added successfully", "");
					} else {
						islabels.add(label);
						note.setLabels(islabels);
						repository.save(note);
						return responce.sendResponse(200, "label added successfully", "");
					}
				} else {

					return responce.sendResponse(400, "labels not found", "");
				}
			} else {
				return responce.sendResponse(400, "note is trashed", "");
			}

		}
		return responce.sendResponse(200, "reached last", "");

	}

	@Override
	public Responce deleteLabelFromNote(String token, long noteid, long labelid) {
		String userid = tokenUtility.verifyToken(token);
		Optional<Note> opnote = repository.findByUseridAndNoteid(userid, noteid);
		Optional<Label> oplabel = labelrepository.findByUserIdAndLabelId(userid, labelid);

		if (opnote.isPresent() && oplabel.isPresent()) {
			Note note = opnote.get();
			Label label = oplabel.get();
			note.setUpdatedTime(TimeUtility.todayDate());
			List<Label> islabels = note.getLabels();
			if (!note.isTrash()) {

				if (islabels != null) {
					Optional<Label> plabels = islabels.stream().filter(l -> l.getLabelId() == label.getLabelId())
							.findFirst();
					if (plabels.isPresent()) {
						islabels.remove(plabels.get());
						note.setLabels(islabels);
						repository.save(note);
					} else {
						return responce.sendResponse(400, "label not found for note", "");
					}
				}
//			else
//			{
//				return responce.sendResponse(400, "no labels are present for this note", "");
//			}
			} else {
				return responce.sendResponse(400, "note is in trash", "");
			}
		} else {
			return responce.sendResponse(400, "note or label is not present", "");
		}
		return responce.sendResponse(200, "label removed", "");
	}

	public Note getallRedis(long noteid) throws IllegalArgumentException, UnsupportedEncodingException {

		return redis.getMapAsSingleEntry("notes", noteid);

	}

	public Note getValue(String key) {
		return redis.getValue(key);
	}

}