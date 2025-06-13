package dto;

import java.io.Serializable;

public class CheeseUser implements Serializable {
	private int id;           /* ID */
	private String name;      /* ユーザー名 */
	private String password;  /* パスワード */
	private int thema;        /* テーマ */
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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getThema() {
		return thema;
	}
	
	public void setThema(int thema) {
		this.thema = thema;
	}
	
	public String getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdateAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public String getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	public CheeseUser(int id, String name, String password,
			int thema, String updatedAt, String createdAt) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.thema = thema;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
	}
	
	public CheeseUser() {
		this(0, "", "", 0, "", "");
	}
}
