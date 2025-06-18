package test;

import java.util.ArrayList;
import java.util.List;

import dao.CheesePhraseDAO;
import dto.CheesePhrase;

public class CheesePhraseDAOTest {
	
	public static void showAllData(List<CheesePhrase> phraseList) {
		for (CheesePhrase phrase :  phraseList) {
			System.out.println("--------------------------------------");
			System.out.println(phrase.getId());
			System.out.println(phrase.getName());
			System.out.println(phrase.getRemarks());
			System.out.println(phrase.getPath());
			System.out.println(phrase.getUserId());
			System.out.println(phrase.getCreatedAt());
			System.out.println(phrase.getUpdatedAt());
			System.out.println("-------------------------------------");
			System.out.println();
		}
	}

	public static void main(String[] args) {
		CheesePhraseDAO dao = new CheesePhraseDAO();
		// selectのテスト
		int userId = 1;
		System.out.println("---------- select()のテスト1 ----------");
		List<String> searchWordList = new ArrayList<String>(); 
		List<String> searchTagList = new ArrayList<String>(); 
		searchWordList.add("3");
		List<CheesePhrase> phraseList = dao.select(searchWordList, searchTagList, "", userId);
		CheesePhraseDAOTest.showAllData(phraseList);
		
		System.out.println("---------- select()のテスト2 ----------");
		searchWordList.clear();
		searchTagList.clear();
		searchTagList.add("A");
		phraseList = dao.select(searchWordList, searchTagList, "", userId);
		CheesePhraseDAOTest.showAllData(phraseList);
		
		System.out.println("---------- select()のテスト3 ----------");
		searchWordList.clear();
		searchTagList.clear();
		searchTagList.add("D");
		phraseList = dao.select(searchWordList, searchTagList, "", userId);
		CheesePhraseDAOTest.showAllData(phraseList);
		
		System.out.println("---------- select()のテスト4 ----------");
		searchWordList.clear();
		searchTagList.clear();
		searchTagList.add("B");
		searchTagList.add("C");
		phraseList = dao.select(searchWordList, searchTagList, "", userId);
		CheesePhraseDAOTest.showAllData(phraseList);
		
		System.out.println(dao.getNextId());
		
		
		// insertのテスト
//		System.out.println("---------- insert()のテスト1 ----------");
//		CheesePhrase insertPhrase = new CheesePhrase(0, "test3", "", "", 1, "", "");
//		if(dao.insert(insertPhrase)) {
//			System.out.println("登録成功");
//			List<String> searchWordList = new ArrayList<String>(); 
//			List<String> searchTagList = new ArrayList<String>(); 
//			int userId = 1;
//			List<CheesePhrase> phraseList = dao.select(searchWordList, searchTagList, userId);
//			CheesePhraseDAOTest.showAllData(phraseList);
//		}
//		else {
//			System.out.println("登録失敗");
//		}

	}

}
