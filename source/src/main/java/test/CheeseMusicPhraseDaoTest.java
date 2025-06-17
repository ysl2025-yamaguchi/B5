package test;

import java.util.List;

import dao.CheeseMusicPhraseDao;
import dto.CheeseMusicPhrase;

public class CheeseMusicPhraseDaoTest {
	public static void showAllData(List<CheeseMusicPhrase> cardList) {
		for (CheeseMusicPhrase card : cardList) {
			System.out.println("ID：" + card.getId());
			System.out.println("曲ID：" + card.getMusicId());
			System.out.println("フレーズID：" + card.getPhraseId());
			System.out.println("フレーズタイトル：" + card.getTitle());
			System.out.println("メモ：" + card.getRemarks());
			System.out.println("順番：" + card.getPhraseOrder());
			System.out.println("更新日時：" + card.getUpdatedAt());
			System.out.println("作成日時：" + card.getCreatedAt());

			System.out.println();
		}
	}

	public static void main(String[] args) {
		CheeseMusicPhraseDao dao = new CheeseMusicPhraseDao();

		// select()のテスト1
		System.out.println("---------- select()のテスト1 ----------");
		List<CheeseMusicPhrase> cardListSel1 = dao.select(1);
		CheeseMusicPhraseDaoTest.showAllData(cardListSel1);

//		// select()のテスト2
//		System.out.println("---------- select()のテスト2 ----------");
//		List<CheeseMusicPhrase> cardListSel2 = dao.select(new CheeseMusicPhrase(0, 0, 0, "", "", 0, "", ""));
//		CheeseMusicPhraseDaoTest.showAllData(cardListSel2);
		

		// insert()のテスト
		System.out.println("---------- insert()のテスト ----------");
		CheeseMusicPhrase insRec = new CheeseMusicPhrase(0, 1, 110, "アフターアワー", "My Hair is Bad の楽曲", 6, "", "");
		if (dao.insert(insRec)) {
			System.out.println("登録成功！");
			List<CheeseMusicPhrase> cardListIns = dao.select(1);
			CheeseMusicPhraseDaoTest.showAllData(cardListIns);
		} else {
			System.out.println("登録失敗！");
		}

		// update()のテスト
		System.out.println("---------- update()のテスト ----------");
		List<CheeseMusicPhrase> cardListUp = dao.select(1);
		CheeseMusicPhrase upRec = cardListUp.get(5);
		upRec.setTitle("元彼氏として");
		upRec.setRemarks("タイトルを「元彼氏として」に更新しました。");
		if (dao.update(upRec)) {
			System.out.println("更新成功！");
			cardListUp = dao.select(1);
			CheeseMusicPhraseDaoTest.showAllData(cardListUp);
		} else {
			System.out.println("更新失敗！");
		}

		// delete()のテスト
		System.out.println("---------- delete()のテスト ----------");
		List<CheeseMusicPhrase> cardListDel = dao.select(1);
		CheeseMusicPhrase delRec = cardListDel.get(5);
		if (dao.delete(delRec)) {
			System.out.println("削除成功！");
			cardListDel = dao.select(1);
			CheeseMusicPhraseDaoTest.showAllData(cardListDel);
		} else {
			System.out.println("削除失敗！");
		}
	}
}