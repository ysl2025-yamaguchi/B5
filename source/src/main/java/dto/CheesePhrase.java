package dto;

import java.io.Serializable;

public class CheesePhrase implements Serializable {
	private int id;
	private String name;
	private String remarks;
	private String path;
	private String updatedAt;
	private String createdAt;
	private int userId;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRemarks() {
		return remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
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
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public CheesePhrase() {
		this(0, "", "", "", "", "", 0);
	}
	
	public CheesePhrase(int id, String name, String remarks, String path, String updatedAt, String createdAt, int userId) {
		this.id = id;
		this.name = name;
		this.remarks = remarks;
		this.path = path;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
		this.userId = userId;
	}
}
