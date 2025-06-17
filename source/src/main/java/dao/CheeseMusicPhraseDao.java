package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.CheeseMusicPhrase;

public class CheeseMusicPhraseDao {
	// 引数card指定された項目で検索して、取得されたデータのリストを返す
		public List<CheeseMusicPhrase> select(int musicId) {
			Connection conn = null;
			List<CheeseMusicPhrase> cardList = new ArrayList<CheeseMusicPhrase>();

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/B5?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SQL文を準備する
				String sql = "SELECT id, music_id, phrase_id, title, remarks, phrase_order, updated_at, created_at FROM musics_phrases WHERE music_id = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
					pStmt.setInt(1, musicId);


				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				while (rs.next()) {
					CheeseMusicPhrase  cheeseMusicPhrase = new CheeseMusicPhrase(rs.getInt("id"), 
													rs.getInt("music_id"), 
													rs.getInt("phrase_id"), 
													rs.getString("title"), 
													rs.getString("remarks"), 
													rs.getInt("phrase_order"), 
													rs.getString("updated_at"), 
													rs.getString("created_at"));
					cardList.add(cheeseMusicPhrase);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				cardList = null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				cardList = null;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						cardList = null;
					}
				}
			}

			// 結果を返す
			return cardList;
		}

		// 引数cardで指定されたレコードを登録し、成功したらtrueを返す
		public boolean insert(CheeseMusicPhrase card) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/B5?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SQL文を準備する
				String sql = "INSERT INTO musics_phrases (music_id, phrase_id, title, remarks, phrase_order) VALUES (?, ?, ?, ?, ?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
					pStmt.setInt(1, card.getMusicId());

					pStmt.setInt(2, card.getPhraseId());
				
				if (card.getTitle() != null) {
					pStmt.setString(3, card.getTitle());
				} else {
					pStmt.setString(3, "");
				}
				if (card.getRemarks() != null) {
					pStmt.setString(4, card.getRemarks());
				} else {
					pStmt.setString(4, "");
				}

					pStmt.setInt(5, card.getPhraseOrder());

				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					result = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

			// 結果を返す
			return result;
		}

		// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
		public boolean update(CheeseMusicPhrase card) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/B5?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SQL文を準備する
				String sql = "UPDATE musics_phrases SET title=?, remarks=?, phrase_order=?  WHERE id=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				if (card.getTitle() != null) {
					pStmt.setString(1, card.getTitle());
				} else {
					pStmt.setString(1, "");
				}
				if (card.getRemarks() != null) {
					pStmt.setString(2, card.getRemarks());
				} else {
					pStmt.setString(2, "");
				}
				
				pStmt.setInt(3, card.getPhraseOrder());
				
				pStmt.setInt(4, card.getId());

				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					result = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

			// 結果を返す
			return result;
		}

		// 引数cardで指定された番号のレコードを削除し、成功したらtrueを返す
		public boolean delete(CheeseMusicPhrase card) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/B5?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SQL文を準備する
				String sql = "DELETE FROM musics_phrases WHERE id=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setInt(1, card.getId());

				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					result = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

			// 結果を返す
			return result;
		}
	
}
