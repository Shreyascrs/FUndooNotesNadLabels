package com.bridgeit.fundoo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.fundoo.dto.Labeldto;
import com.bridgeit.fundoo.model.Label;
import com.bridgeit.fundoo.responce.Responce;
import com.bridgeit.fundoo.service.LabelServiceImpl;

@RestController
@RequestMapping("/label")
public class LabelController {

	@Autowired
	LabelServiceImpl labelservice;
	
	@PostMapping
	public Responce createlabel(@RequestHeader String token, @RequestBody Labeldto labeldto)
	{
		Responce responce=labelservice.createlabel(token, labeldto);
		return responce;
	}
	
	@PutMapping
	public Responce updateLabel(@RequestHeader String token,@RequestHeader long labelid,@RequestBody Labeldto labeldto)
	{
		Responce resp=labelservice.updateLabel(token, labelid, labeldto);
		 return resp;
	}
	
	@GetMapping
	public List<Label> getlabels(@RequestHeader String token)
	{
		List<Label> resp=labelservice.getAllLabels(token);
		return resp;
	}
	
	@DeleteMapping
	public Responce deletelabel(@RequestHeader long labelid)
	{
		Responce resp=labelservice.deleteLabel(labelid);
		return resp;
	}
}
