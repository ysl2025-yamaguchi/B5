package test;



import java.util.List;

import dao.CheesePhraseTagDao;
import dto.CheesePhraseTag;


public class CheesePhraseTagDaoTest {
	       public static void showAllData(List<CheesePhraseTag> tagList) {
	        for (CheesePhraseTag tag : tagList) {
	            System.out.println("ID：" + tag.getId());
	            System.out.println("フレーズID：" + tag.getPhraseId());
	            System.out.println("タグID：" + tag.getTagId());
	            System.out.println("フレーズ名：" + tag.getPhraseName());
	            System.out.println("フレーズ備考：" + tag.getPhraseRemarks());
	            System.out.println("タグ名：" + tag.getTagName());
	        }
	        }
	        public static void main(String[] args) {
	            CheesePhraseTagDao dao = new CheesePhraseTagDao();
	           
		        System.out.println("---------- SELECT テスト ----------");
		        List<CheesePhraseTag> list1 = dao.selectPhraseTagInfo(1);
		        showAllData(list1);
	        
		        System.out.println("---------- insert()のテスト ----------");
		    
		        CheesePhraseTag insRec = new CheesePhraseTag(0, 1, 1, "", "");
		        if (dao.insert(insRec)) {
		            System.out.println("登録成功！");
		            List<CheesePhraseTag> cardListIns = dao.selectPhraseTagInfo(1);
		            CheesePhraseTagDaoTest.showAllData(cardListIns);
		        } else {
		            System.out.println("登録失敗！");
		        }

				// update()のテスト
		        System.out.println("---------- update()のテスト ----------");
		        List<CheesePhraseTag> cardListUp = dao.selectPhraseTagInfo(1);
		        CheesePhraseTag upRec = cardListUp.get(0);
		        upRec.setPhraseId(1);  
		        upRec.setTagId(2); 
                if (dao.update(upRec)) {
		            System.out.println("更新成功！");
		            CheesePhraseTagDaoTest.showAllData(cardListUp);
		        } else {
		            System.out.println("更新失敗！");
		        }
				// delete()のテスト
				System.out.println("---------- delete()のテスト ----------");
				List<CheesePhraseTag> cardListDel = dao.selectPhraseTagInfo(1);
				CheesePhraseTag delRec = cardListDel.get(0);
				if (dao.delete(delRec)) {
					System.out.println("削除成功！");
					
					CheesePhraseTagDaoTest.showAllData(cardListDel);
				} else {
					System.out.println("削除失敗！");
				}
	        }
}
		      
	        
	        
