package dto;

import java.io.Serializable;

public class CheeseTag implements Serializable {
	private int id;           /* ID */
	private String name;      /* ユーザー名 */
	private int userId;        /* ユーザーID */
	private String updatedAt; /* 更新日時 */
	private String createdAt; /* 作成日時 */
	
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
	
	public CheeseTag() {
		this(0, "", 0, "", "");
	}
	
	public CheeseTag(int id, String name, int userId, String updatedAt, String createdAt) {
		super();
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
	}
	
}
