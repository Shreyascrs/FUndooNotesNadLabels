package com.bridgeit.fundoo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Note {

	public Note() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long noteid;
	private String userid;
	private String title;
	private String color;
	private String description;
	private String createdTime;
	private String updatedTime;
	private boolean archive;
	private boolean trash;
	private boolean isPin;

	



	public long getNoteid() {
		return noteid;
	}

	public void setNoteid(long noteid) {
		this.noteid = noteid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	public boolean isTrash() {
		return trash;
	}

	public void setTrash(boolean trash) {
		this.trash = trash;
	}

	public boolean isPin() {
		return isPin;
	}

	public void setPin(boolean isPin) {
		this.isPin = isPin;
	}

	public Note(long noteid, String userid, String title, String color, String description, String createdTime,
			String updatedTime, boolean archive, boolean trash, boolean isPin) {
		super();
		this.noteid = noteid;
		this.userid = userid;
		this.title = title;
		this.color = color;
		this.description = description;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.archive = archive;
		this.trash = trash;
		this.isPin = isPin;
	}

	@Override
	public String toString() {
		return "Note [noteid=" + noteid + ", userid=" + userid + ", title=" + title + ", color=" + color
				+ ", description=" + description + ", createdTime=" + createdTime + ", updatedTime=" + updatedTime
				+ ", archive=" + archive + ", trash=" + trash + ", isPin=" + isPin + "]";
	}





	
}
