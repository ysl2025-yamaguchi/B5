package test;

import java.util.List;

import dao.CheeseMusicDao;
import dto.CheeseMusic;

public class CheeseMusicDAOTest {
	public static void showAllData(List<CheeseMusic> cardList) {
		for (CheeseMusic card : cardList) {
			System.out.println("番号：" + card.getId());
			System.out.println("曲名：" + card.getName());
			System.out.println("ユーザーID：" + card.getUserId());
			System.out.println("作成日時：" + card.getCreatedAt());
			System.out.println("更新日時：" + card.getUpdatedAt());
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		CheeseMusicDao dao = new CheeseMusicDao();
		
//		
//		// insert()のテスト
//		System.out.println("---------- insert()のテスト ----------");
//		CheeseMusic insRec = new CheeseMusic(0, "チータラ", 3, "", "");
//		if (dao.insert(insRec)) {
//			System.out.println("登録成功！");
//			List<CheeseMusic> cardListIns = dao.select(new CheeseMusic(0, "", 0, "", ""));
//			CheeseMusicDAOTest.showAllData(cardListIns);
//		} else {
//			System.out.println("登録失敗！");
//		}
//		
//		// select()のテスト1
//		System.out.println("---------- select()のテスト1 ----------");
//		List<CheeseMusic> cardListSel1 = dao.select(new CheeseMusic(0, "チーズ", 0, "",""));
//		CheeseMusicDAOTest.showAllData(cardListSel1);
//		
//		// select()のテスト2
//		System.out.println("---------- select()のテスト2 ----------");
//		List<CheeseMusic> cardListSel2 = dao.select(new CheeseMusic(0, "", 0, "", ""));
//		CheeseMusicDAOTest.showAllData(cardListSel2);
//		
//		// 並び替え（新しい順）のテスト
//		System.out.println("---------- 並び替え：新しい順 ----------");
//		List<CheeseMusic> sortedNew = dao.select(new CheeseMusic(0, "", 0, "", ""), "new");
//		CheeseMusicDAOTest.showAllData(sortedNew);
//
//		// 並び替え（古い順）のテスト
//		System.out.println("---------- 並び替え：古い順 ----------");
//		List<CheeseMusic> sortedOld = dao.select(new CheeseMusic(0, "", 0, "", ""), "old");
//		CheeseMusicDAOTest.showAllData(sortedOld);
//
//		// 並び替え（更新順）のテスト
//		System.out.println("---------- 並び替え：更新順 ----------");
//		List<CheeseMusic> sortedUpdate = dao.select(new CheeseMusic(0, "", 0, "", ""), "update");
//		CheeseMusicDAOTest.showAllData(sortedUpdate);
//		
//		
		// delete()のテスト
		System.out.println("---------- delete()のテスト ----------");
		List<CheeseMusic> cardListDel = dao.select(new CheeseMusic(4, "", 0, "", ""));
		CheeseMusic delRec = cardListDel.get(0);
		System.out.println(cardListDel.size());
		if (dao.delete(delRec)) {
		System.out.println("削除成功！");
		cardListDel = dao.select(new CheeseMusic(0, "", 0, "", ""));
		CheeseMusicDAOTest.showAllData(cardListDel);
		} else {
			System.out.println("削除失敗！");
		}
	}
}

