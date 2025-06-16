package dto;

import java.io.Serializable;

public class CheeseMusicPhrase  implements Serializable{
	
	private int id;
	private int musicId;
	private int phraseId;
	private String title;
	private String remarks;
	private int phraseOrder;
	private String updatedAt;
	private String createdAt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMusicId() {
		return musicId;
	}
	public void setMusicId(int musicId) {
		this.musicId = musicId;
	}
	public int getPhraseId() {
		return phraseId;
	}
	public void setPhraseId(int phraseId) {
		this.phraseId = phraseId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getPhraseOrder() {
		return phraseOrder;
	}
	public void setPhraseOrder(int phraseOrder) {
		this.phraseOrder = phraseOrder;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

public CheeseMusicPhrase() {
	this(0, 0, 0, "", "", 0, "", "");
}
	
public CheeseMusicPhrase(int id, int musicId, int phraseId, String title, String remarks, int phraseOrder,
		String updatedAt, String createdAt) {
	super();
	this.id = id;
	this.musicId = musicId;
	this.phraseId = phraseId;
	this.title = title;
	this.remarks = remarks;
	this.phraseOrder = phraseOrder;
	this.updatedAt = updatedAt;
	this.createdAt = createdAt;
}
}

