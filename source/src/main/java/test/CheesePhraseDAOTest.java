package test;

import java.util.ArrayList;
import java.util.List;

import dao.CheesePhraseDAO;
import dto.CheesePhrase;

public class CheesePhraseDAOTest {
	
	public static void showAllData(List<CheesePhrase> phraseList) {
		for (CheesePhrase phrase :  phraseList) {
			System.out.println(phrase.getId());
			System.out.println(phrase.getName());
			System.out.println(phrase.getRemarks());
			System.out.println(phrase.getPath());
			System.out.println(phrase.getUserId());
			System.out.println(phrase.getCreatedAt());
			System.out.println(phrase.getUpdatedAt());
		}
	}

	public static void main(String[] args) {
		CheesePhraseDAO dao = new CheesePhraseDAO();
		// insertのテスト
		System.out.println("---------- insert()のテスト1 ----------");
		CheesePhrase insertPhrase = new CheesePhrase(0, "test", "", "", 1, "", "");
		if(dao.insert(insertPhrase)) {
			System.out.println("登録成功");
			List<String> searchWordList = new ArrayList<String>(); 
			List<String> searchTagList = new ArrayList<String>(); 
			int userId = 1;
			List<CheesePhrase> phraseList = dao.select(searchWordList, searchTagList, userId);
			CheesePhraseDAOTest.showAllData(phraseList);
		}
		else {
			System.out.println("登録失敗");
		}

	}

}
