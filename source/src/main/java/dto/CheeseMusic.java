package dto;

import java.io.Serializable;

public class CheeseMusic implements Serializable {
	
	private int id;				//ID
	private int userId;			//ユーザーID
	private String name;		//曲名
	private String updatedAt;	//更新日時
	private String createdAt;	//作成日時
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	public CheeseMusic() {
		this(0, 0, "", "", "");
	}
	
	public CheeseMusic(int id, int userId, String name, String updatedAt, String createdAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
	}
	
	
}
