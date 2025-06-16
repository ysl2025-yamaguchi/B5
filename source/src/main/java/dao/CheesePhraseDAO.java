package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.CheesePhrase;

public class CheesePhraseDAO {
	
	public List<CheesePhrase> search(List<String> searchWordList, List<String> searchTagList, int userId) {
		Connection conn = null;
		List<CheesePhrase> phraseList = new ArrayList<CheesePhrase>();
//		
//		try {
//			// JDBCドライバを読み込む
//
//			// データベースに接続する
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/music_cheese?"
//					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
//					"root", "password");
//			
//			// SQL文を準備する
//			StringBuilder sql = new StringBuilder();
//			PreparedStatement pStmt;
//			ResultSet rs;
//			
//			sql.setLength(0);
//			sql.append("SELECT bc.id, bc.user_id, bc.name, bc.gender, bc.company_id, bc.department, ");
//			sql.append("bc.position, bc.phone, bc.email, bc.created_at, bc.updated_at, bc.remarks, ");
//			sql.append("c.name AS company_name, c.address AS company_address, c.tel AS company_tel, c.fax AS company_fax, "
//					+ "c.zipcode AS company_zipcode, c.member_count AS company_member_count ");
//			sql.append("FROM business_card bc ");
//			sql.append("JOIN company c ON bc.company_id = c.id ");
//			sql.append("WHERE bc.user_id = ?");
//			
//			if (!searchWordList.isEmpty()) {
//				sql.append(" AND (");
//				for (int i = 0; i < searchWordList.size(); i++) {
//					if (i != 0) {
//						sql.append(" AND ");
//					}
//					sql.append("(bc.name LIKE ? OR bc.department LIKE ? OR bc.position LIKE ? ");
//					sql.append("OR bc.remarks LIKE ? OR c.name LIKE ?)");
//				}
//				sql.append(")");
//			}
////			System.out.println(sql);
//			
//			pStmt = conn.prepareStatement(sql.toString());
//			pStmt.setInt(1, userId);
//			int i;
//			for (i = 0; i < searchWordList.size(); i++) {
//				pStmt.setString(i * 5 + 2, "%" + searchWordList.get(i) + "%");
//				pStmt.setString(i * 5 + 3, "%" + searchWordList.get(i) + "%");
//				pStmt.setString(i * 5 + 4, "%" + searchWordList.get(i) + "%");
//				pStmt.setString(i * 5 + 5, "%" + searchWordList.get(i) + "%");
//				pStmt.setString(i * 5 + 6, "%" + searchWordList.get(i) + "%");
//			}
//			
//			// SQL文を実行し、結果表を取得する
//			rs = pStmt.executeQuery();
//			
//			// 結果表をコレクションにコピーする
//			while (rs.next()) {
//				Bc bc = new Bc(
//						rs.getInt("id"), 
//						rs.getInt("user_id"), 
//						rs.getString("name"),
//						rs.getInt("gender"),
//						rs.getInt("company_id"), 
//						rs.getString("department"),
//						rs.getString("position"),
//						rs.getString("phone"),
//						rs.getString("email"),
//						rs.getString("created_at"),
//						rs.getString("updated_at"),
//						rs.getString("remarks"),
//						rs.getString("company_name"),
//						rs.getInt("company_member_count"),
//						rs.getString("company_zipcode"),
//						rs.getString("company_tel"),
//						rs.getString("company_fax"),
//						rs.getString("company_address")
//						);
//				cardList.add(bc);
//			}
//			
//		}
//		catch (SQLException e) {
//		e.printStackTrace();
//		cardList = null;
//		} 
//		catch (ClassNotFoundException e) {
//			e.printStackTrace();
//			cardList = null;
//		} 
//		finally {
//			// データベースを切断
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//					cardList = null;
//				}
//			} 
//		}
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
