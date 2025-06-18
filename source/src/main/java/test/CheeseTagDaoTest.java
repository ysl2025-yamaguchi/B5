package test;

import java.util.List;

import dao.CheeseTagDao;
import dto.CheeseTag;

public class CheeseTagDaoTest {
	public static void showAllData(List<CheeseTag> cardList) {
		for (CheeseTag card : cardList) {
			System.out.println("ID：" + card.getId());
			System.out.println("ユーザー名：" + card.getName());
			System.out.println("ユーザーID：" + card.getUserId());
			System.out.println("更新日時：" + card.getUpdatedAt());
			System.out.println("作成日時：" + card.getCreatedAt());
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		CheeseTagDao dao = new CheeseTagDao();
		
		// insert()のテスト
		System.out.println("---------- insert()のテスト ----------");
		CheeseTag insRec = new CheeseTag(0, "yama", 0, "", "");
		if (dao.insert(insRec)) {
			System.out.println("登録成功！");
			List<CheeseTag> cardListIns = dao.select(new CheeseTag(0, "", 0, "", ""));
			CheeseTagDaoTest.showAllData(cardListIns);
		} else {
			System.out.println("登録失敗！");
		}
		
		// delete()のテスト
		System.out.println("---------- delete()のテスト ----------");
		List<CheeseTag> cardListDel = dao.select(new CheeseTag(0, "yama", 0, "", ""));
		CheeseTag delRec = cardListDel.get(0);
		if (dao.delete(delRec)) {
			System.out.println("削除成功！");
			cardListDel = dao.select(new CheeseTag(0, "", 0, "", ""));
			CheeseTagDaoTest.showAllData(cardListDel);
		} else {
			System.out.println("削除失敗！");
		}
	}

}
