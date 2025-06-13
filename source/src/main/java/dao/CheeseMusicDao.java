package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.CheeseMusic;

public class CheeseMusicDao {
	// 引数card指定された項目で検索して、取得されたデータのリストを返す
	public List<CheeseMusic> select(CheeseMusic card) {
		Connection conn = null;
		List<CheeseMusic> cardList = new ArrayList<CheeseMusic>();
		
		try {
		// JDBCドライバを読み込む
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/music_cheese?"
							+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
							"root", "password");

		// SQL文を準備する
		String sql = "SELECT id, name, created_at, updated_at, user_id"
					+ "FROM MusicCheese "
					+ "WHERE AND name LIKE ? AND user_id LIKE ?"
					+ "ORDER BY number";
					
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		// SQL文を完成させる
		if (card.getName() != null) {
			pStmt.setString(1, "%" + card.getName() + "%");
		} else {
			pStmt.setString(1, "%");
		}
		if (card.getName() != null) {
			pStmt.setString(4, "%" + card.getName() + "%");
		} else {
			pStmt.setString(4, "%");
		}
		
		}
}
