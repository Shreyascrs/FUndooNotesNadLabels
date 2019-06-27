package com.bridgeit.fundoo.service;

import java.util.List;
import com.bridgeit.fundoo.dto.Labeldto;
import com.bridgeit.fundoo.model.Label;
import com.bridgeit.fundoo.responce.Responce;

public interface ILabelService {
	public Responce createlabel(String token,Labeldto labeldto);
	public Responce updateLabel(String token,long labelId,Labeldto labeldto);
	public List<Label> getAllLabels(String token);
	public Responce deleteLabel(long labelId);
}
