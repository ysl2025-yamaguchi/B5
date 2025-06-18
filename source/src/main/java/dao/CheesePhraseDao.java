package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.CheesePhrase;

public class CheesePhraseDao {
	
	// キーワードもしくはタグ名で検索
	public List<CheesePhrase> select(List<String> searchWordList, List<String> searchTagList, String order, int userId) {
		Connection conn = null;
		List<CheesePhrase> phraseList = new ArrayList<CheesePhrase>();
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			// SQL文を準備する
			StringBuilder sql = new StringBuilder();
			sql.setLength(0);
			sql.append("SELECT phrases.id, phrases.name, phrases.remarks, phrases.path, phrases.user_id, "
					+ "phrases.created_at, phrases.updated_at, "
					+ "GROUP_CONCAT(tags.name ORDER BY tags.name SEPARATOR ' ') AS tag_concat "
					+ "FROM phrases " 
					+ "LEFT JOIN phrases_tags ON phrases.id = phrases_tags.phrase_id "
					+ "LEFT JOIN tags ON phrases_tags.tag_id = tags.id "
					+ "GROUP BY phrases.id  HAVING phrases.user_id = ? ");
//			sql.append("FROM phrases LEFT JOIN phrases_tags ON phrases.id = phrases_tags.phrase_id ");
//			sql.append("LEFT JOIN tags ON phrases_tags.tag_id = tags.id ");
//			sql.append("WHERE phrases.user_id = ?");
			
			for (int i = 0; i < searchWordList.size(); i++) {
				sql.append(" AND ");
				sql.append("phrases.name LIKE ?");
			}
			
			for (int i = 0; i < searchTagList.size(); i++) {
				sql.append(" AND ");
				sql.append("tag_concat LIKE ?");
			}
			
			switch (order) {
			case "created_desc":
				sql.append(" ORDER BY created_at DESC ");
				break;
			case "created_asc":
				sql.append(" ORDER BY created_at ASC ");
				break;
			case "updated_desc":
				sql.append(" ORDER BY updated_at DESC ");
				break;
			case "updated_asc":
				sql.append(" ORDER BY updated_at ASC ");
				break;
			default:
				sql.append(" ORDER BY created_at DESC ");
				break;
			}
			
			PreparedStatement pStmt;
			pStmt = conn.prepareStatement(sql.toString());
			pStmt.setInt(1, userId);
			int i, j;
			for (i = 0; i < searchWordList.size(); i++) {
				pStmt.setString(i + 2, "%" + searchWordList.get(i) + "%");
			}
			for (j = 0; j < searchTagList.size(); j++) {
				pStmt.setString(j + i + 2, "%" + searchTagList.get(j) + "%");
			}
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs;
			rs = pStmt.executeQuery();
			
			// 結果表をコレクションにコピーする
			while (rs.next()) {
				CheesePhrase cheesePhrase = new CheesePhrase(
						rs.getInt("phrases.id"), 
						rs.getString("phrases.name"), 
						rs.getString("phrases.remarks"),
						rs.getString("phrases.path"),
						rs.getInt("phrases.user_id"), 
						rs.getString("phrases.created_at"),
						rs.getString("phrases.updated_at")
						);
				phraseList.add(cheesePhrase);
			}
			
		}
		catch (SQLException e) {
		e.printStackTrace();
			phraseList = null;
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			phraseList = null;
		} 
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					phraseList = null;
				}
			} 
		}
		
		return phraseList;
	}
	
	public boolean insert(CheesePhrase phrase) {
		Connection conn = null;
		boolean result = false;
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "INSERT INTO phrases (name, remarks, path, user_id) VALUES (?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (phrase.getName() != null) {
				pStmt.setString(1, phrase.getName());
			} else {
				pStmt.setString(1, "");
			}
			if (phrase.getRemarks() != null) {
				pStmt.setString(2, phrase.getRemarks());
			} else {
				pStmt.setString(2, "");
			}
			if (phrase.getPath() != null) {
				pStmt.setString(3, phrase.getPath());
			} else {
				pStmt.setString(3, "");
			}
			if (phrase.getUserId() != 0) {
				pStmt.setInt(4, phrase.getUserId());
			} else {
				pStmt.setString(4, "");
			}
			
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
		
		return result;
	}
	
	// 引数cardで指定された番号のレコードを削除し、成功したらtrueを返す
	public boolean delete(CheesePhrase phrase) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "DELETE FROM phrases WHERE id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, phrase.getId());

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
	
	public boolean update(CheesePhrase phrase) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "UPDATE phrases SET name=?, remarks=?"
					+ "WHERE id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (phrase.getName() != null) {
				pStmt.setString(1, phrase.getName());
			} else {
				pStmt.setString(1, "");
			}
			if (phrase.getRemarks() != null) {
				pStmt.setString(2, phrase.getRemarks());
			} else {
				pStmt.setString(2, "");
			}
			
			pStmt.setInt(3, phrase.getId());
			
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
	
	public int getNextId() {
		Connection conn = null;
		int nextId = -1;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b5?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "SHOW TABLE STATUS FROM b5 LIKE 'phrases'";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs;
			rs = pStmt.executeQuery();
			
			// 結果表をコレクションにコピーする
			while (rs.next()) {
				nextId = rs.getInt("auto_increment");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			nextId = -1;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			nextId = -1;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					nextId = -1;
				}
			}
		}

		// 結果を返す
		return nextId;
	}
	
}
