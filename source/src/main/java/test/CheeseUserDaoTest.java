package test;

import dao.CheeseUserDao;
import dto.CheeseUser;

public class CheeseUserDaoTest {
	public static void main(String[] args) {
		testIsLoginOK1();
		testIsLoginOK2();
		testIsRegistOK1();
	}
	
	// ユーザーが見つかる場合のテスト
	public static void testIsLoginOK1() {
		CheeseUserDao dao = new CheeseUserDao();
		if (dao.isLoginOK(new CheeseUser(0, "yama", "password", 0, "", ""))) {
			System.out.println("testIsLoginOK1：テストが成功しました");
		} else {
			System.out.println("testIsLoginOK1：テストが失敗しました");
		}
	}
	
	// ユーザーが見つからない場合のテスト
	public static void testIsLoginOK2() {
		CheeseUserDao dao = new CheeseUserDao();
		if (!dao.isLoginOK(new CheeseUser(0, "yamaren", "password", 0, "", ""))) {
			System.out.println("testIsLoginOK2：テストが成功しました");
		} else {
			System.out.println("testIsLoginOK2：テストが失敗しました");
		}
	}
	
	//登録できる場合のテスト
	public static void testIsRegistOK1() {
		CheeseUserDao dao = new CheeseUserDao();
		if (dao.isRegistOK(new CheeseUser(0, "yamarennnn", "password", 0, "", ""))) {
			System.out.println("testIsRegistOK1：テストが成功しました");
		} else {
			System.out.println("testIsRegistOK1：テストが失敗しました");
		}
	}
}
