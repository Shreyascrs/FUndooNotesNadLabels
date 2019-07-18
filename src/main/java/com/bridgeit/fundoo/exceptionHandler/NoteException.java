package com.bridgeit.fundoo.exceptionHandler;

public class NoteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 100L;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NoteException(String message) {
		super(message);
		this.message = message;

	}

}
