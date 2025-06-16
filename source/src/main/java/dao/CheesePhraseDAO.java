package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.CheesePhrase;

public class CheesePhraseDAO {
	
	public List<CheesePhrase> search(List<String> searchWordList, List<String> searchTagList, int userId) {
		Connection conn = null;
		List<CheesePhrase> phraseList = new ArrayList<CheesePhrase>();
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/music_cheese?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			// SQL文を準備する
			StringBuilder sql = new StringBuilder();
			PreparedStatement pStmt;
			ResultSet rs;
			
			sql.setLength(0);
			sql.append("SELECT DISTINCT phrases.id, phrases.name, phrases.remarks, phrases.path, phrases.user_id, phrases.created_at, phrases.updated_at");
			sql.append("FROM phrases LEFT JOIN phrases_tags ON phrases.id = phrases_tags.phrase_id");
			sql.append("JOIN tags ON phrases_tags.tag_id = tags.id");
			sql.append("WHERE phrases.user_id = ?");
			
//			sql.append("SELECT bc.id, bc.user_id, bc.name, bc.gender, bc.company_id, bc.department, ");
//			sql.append("bc.position, bc.phone, bc.email, bc.created_at, bc.updated_at, bc.remarks, ");
//			sql.append("c.name AS company_name, c.address AS company_address, c.tel AS company_tel, c.fax AS company_fax, "
//					+ "c.zipcode AS company_zipcode, c.member_count AS company_member_count ");
//			sql.append("FROM business_card bc ");
//			sql.append("JOIN company c ON bc.company_id = c.id ");
//			sql.append("WHERE bc.user_id = ?");
			
			for (int i = 0; i < searchWordList.size(); i++) {
				sql.append(" AND ");
				sql.append("phrases.name LIKE ?");
			}
			
			for (int i = 0; i < searchTagList.size(); i++) {
				sql.append(" AND ");
				sql.append("tags.name LIKE ?");
			}
			
//			System.out.println(sql);
			
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/music_cheese?"
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/music_cheese?"
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/music_cheese?"
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
	
}
