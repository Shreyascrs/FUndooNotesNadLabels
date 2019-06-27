package com.bridgeit.fundoo.service;

import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeit.fundoo.dto.Labeldto;
import com.bridgeit.fundoo.model.Label;
import com.bridgeit.fundoo.repository.LabelRepo;
import com.bridgeit.fundoo.responce.Responce;
import com.bridgeit.fundoo.utility.TokenUtility;

@Service
public class LabelServiceImpl implements ILabelService {

	@Autowired
	TokenUtility tokenUtility;

	@Autowired
	ModelMapper mapper;

	@Autowired
	LabelRepo repository;

	@Autowired
	Responce responce;

	public Responce createlabel(String token, Labeldto labeldto) {
		String userid = tokenUtility.verifyToken(token);
		Label label = mapper.map(labeldto, Label.class);
		label.setUserId(userid);
		try {
			repository.save(label);
		} catch (Exception e) {

			return responce.sendResponse(400, "note not saved", "");
		}

		return responce.sendResponse(200, "label created", "");

	}

	@Override
	public Responce updateLabel(String token, long labelId, Labeldto labeldto) {
		String userid = tokenUtility.verifyToken(token);
		Optional<Label> islabel = repository.findByUserIdAndLabelId(userid, labelId);
		if (islabel.isPresent()) {
			Label label = islabel.get();
			label.setLabelName(labeldto.getLabelName());
			try {

				repository.save(label);
				return responce.sendResponse(200, "label updated", "");
			} catch (Exception e) {
				// TODO: handle exception
			}
			return responce.sendResponse(400, "note not saved", "");
		} else {
			return responce.sendResponse(400, "note not found", "");

		}
	}

	@Override
	public List<Label> getAllLabels(String token) {
		String userid = tokenUtility.verifyToken(token);
		List<Label> labels = repository.findByUserId(userid);
		return labels;
	}

	@Override
	public Responce deleteLabel(long labelId) {
		Optional<Label> islabel = repository.findBylabelId(labelId);
		if (islabel.isPresent()) {
			Label label = islabel.get();
			try {
				repository.delete(label);
			} catch (Exception e) {

			}

		} else {
			responce.sendResponse(400, "label not found", "");

		}

		return responce.sendResponse(200, "deleted successfully", "");
	}

}
