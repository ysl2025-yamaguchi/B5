package dto;

import java.io.Serializable;

public class CheesePhraseTag implements Serializable{
	    private int id;
	    private int phraseId;          
	    private int tagId;
	    private String createdAt;
		private String updatedAt;
		 public CheesePhraseTag() {
		    	this(0,0,0,"","");
		    }
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getPhraseId() {
			return phraseId;
		}
		public void setPhraseId(int phraseId) {
			this.phraseId = phraseId;
		}
		public int getTagId() {
			return tagId;
		}
		public void setTagId(int tagId) {
			this.tagId = tagId;
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
		public CheesePhraseTag(int id, int phraseId, int tagId, String createdAt, String updatedAt) {
			super();
			this.id = id;
			this.phraseId = phraseId;
			this.tagId = tagId;
			this.createdAt = createdAt;
			this.updatedAt = updatedAt;
		}
        

	
		
	    
	   
}