package com.bridgeit.fundoo.responce;

import javax.persistence.Entity;

public class Responce {
	public Responce() {
		super();
		// TODO Auto-generated constructor stub
	}


	private int statusCode;
	private String message;
	private String data;

	
	public Responce(int statusCode, String message, String data) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}


	public int getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	public Responce sendResponse(int statusCode, String message, String data) {
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
		return this;
	}

}
