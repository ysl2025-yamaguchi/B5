package test;



import java.util.List;

import dao.CheesePhraseTagDao;
import dto.CheesePhraseTag;


public class CheesePhraseTagDaoTest {
	 public static void showAllData(List<CheesePhraseTag> tagList) {
	        for (CheesePhraseTag tag : tagList) {
	            System.out.println("ID：" + tag.getId());
	            System.out.println("フレーズID：" + tag.getPhraseId());
	            System.out.println("フレーズ名：" + tag.getPhraseName());
	            System.out.println("フレーズメモ：" + tag.getPhraseRemarks());
	            System.out.println("タグID：" + tag.getTagId());
	            System.out.println("タグ名：" + tag.getTagName());
	            
	        }
	    }
            public static void main(String[] args) {
	        CheesePhraseTagDao dao = new CheesePhraseTagDao();

	        // SELECT テスト
	        System.out.println("---------- SELECT テスト ----------");
	        List<CheesePhraseTag> list1 = dao.selectPhraseTagInfo(1); // すべて取得
	        showAllData(list1);

	        // INSERT テスト
	        System.out.println("---------- INSERT テスト ----------");
	        CheesePhraseTag insertTag = new CheesePhraseTag();
	        insertTag.setPhraseId(1);
	        insertTag.setTagId(1);   
	        boolean insertResult = dao.insert(insertTag);
	        System.out.println(insertResult ? "挿入成功！" : "挿入失敗！");
	        showAllData(dao.selectPhraseTagInfo(0));

	        // UPDATE テスト
	        System.out.println("---------- UPDATE テスト ----------");
	        List<CheesePhraseTag> list2 = dao.selectPhraseTagInfo(0);
	        if (!list2.isEmpty()) {
	            CheesePhraseTag updateTag = list2.get(0);
	            updateTag.setPhraseId(2); // 別の存在するIDに変更
	            updateTag.setTagId(2);    // 別の存在するIDに変更
	            boolean updateResult = dao.update(updateTag);
	            System.out.println(updateResult ? "更新成功！" : "更新失敗！");
	        }

	        // DELETE テスト
	        System.out.println("---------- DELETE テスト ----------");
	        List<CheesePhraseTag> list3 = dao.selectPhraseTagInfo(0);
	        if (!list3.isEmpty()) {
	            CheesePhraseTag deleteTag = list3.get(0);
	            boolean deleteResult = dao.delete(deleteTag);
	            System.out.println(deleteResult ? "削除成功！" : "削除失敗！");
	        }

	        // 最終確認
	        System.out.println("---------- 最終状態確認 ----------");
	        showAllData(dao.selectPhraseTagInfo(0));
	    }
	}

