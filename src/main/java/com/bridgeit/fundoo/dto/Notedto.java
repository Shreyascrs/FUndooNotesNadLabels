package com.bridgeit.fundoo.dto;

public class Notedto {

	
	private String title;
	private String description;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Notedto(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}

	public Notedto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Notedto [title=" + title + ", description=" + description + "]";
	}

}
