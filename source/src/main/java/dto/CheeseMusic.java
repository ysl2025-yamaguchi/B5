package dto;

import java.io.Serializable;

public class CheeseMusic implements Serializable {
	
	private int id;				//ID
	private String name;		//曲名
	private int userId;			//ユーザーID
	private String createdAt;	//作成日時
	private String updatedAt;	//更新日時
	
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public CheeseMusic() {
		this(0, "", 0, "", "");
	}
	
	public CheeseMusic(int id, String name, int userId, String createdAt, String updatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
