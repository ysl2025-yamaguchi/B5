package dto;

import java.io.Serializable;

public class CheesePhraseTag implements Serializable{
	    private int id;                
	    private int phraseId;          
	    private String phraseName;
	    private String phraseRemarks;
	    private int tagId;
	    private String tagName;
	    
	    public CheesePhraseTag() {
	    	this(0,0,"","",0,"");
	    }

		public CheesePhraseTag(int id, int phraseId, String phraseName, String phraseRemarks, int tagId,
				String tagName) {
			super();
			this.id = id;
			this.phraseId = phraseId;
			this.phraseName = phraseName;
			this.phraseRemarks = phraseRemarks;
			this.tagId = tagId;
			this.tagName = tagName;
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

		public String getPhraseName() {
			return phraseName;
		}

		public void setPhraseName(String phraseName) {
			this.phraseName = phraseName;
		}

		public String getPhraseRemarks() {
			return phraseRemarks;
		}

		public void setPhraseRemarks(String phraseRemarks) {
			this.phraseRemarks = phraseRemarks;
		}

		public int getTagId() {
			return tagId;
		}

		public void setTagId(int tagId) {
			this.tagId = tagId;
		}

		public String getTagName() {
			return tagName;
		}

		public void setTagName(String tagName) {
			this.tagName = tagName;
		}
}