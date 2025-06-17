package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.CheesePhraseTag;
public class CheesePhraseTagDao {
	 private Connection getConnection() throws SQLException, ClassNotFoundException {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        return DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/music_cheese?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
	            "root", "password"
	        );
	    }

	    public List<CheesePhraseTag> selectPhraseTagInfo(int phraseId) {
	        List<CheesePhraseTag> list = new ArrayList<>();
	        String sql = "SELECT " +
	                "p.id AS phrase_id, " +
	                "p.name AS phrase_name, " +
	                "p.remarks AS phrase_remarks, " +
	                "t.id AS tag_id, " +
	                "t.name AS tag_name " +
	                "FROM phrases p " +
	                "JOIN phrases_tags pt ON p.id = pt.phrase_id " +
	                "JOIN tags t ON pt.tag_id = t.id " +
	                "WHERE p.id = ?";
	        try (Connection conn = getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setInt(1, phraseId);
	            ResultSet rs = ps.executeQuery();
	        
	           while (rs.next()) {
	            	    CheesePhraseTag dto = new CheesePhraseTag();
	            	    rs.getInt("id");
	            	    rs.getInt("phrase_id");
	            	    rs.getInt("tag_id");
	            	  
	            	 list.add(dto);
	            	}
	               

	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
	   

	// 引数cardで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(CheesePhraseTag tag) {
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
			String sql = "INSERT INTO phrases_tags (phrase_id, tag_id) VALUES (?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// SQL文を完成させる
			pStmt.setInt(1, tag.getPhraseId());
	        pStmt.setInt(2, tag.getTagId());

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
	public boolean update(CheesePhraseTag tag) {
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
			String sql = "UPDATE phrases_tags SET phrase_id = ?, tag_id = ? WHERE id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			 pStmt.setInt(1, tag.getPhraseId());
		     pStmt.setInt(2, tag.getTagId());
		     pStmt.setInt(3, tag.getId());
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
	public boolean delete(CheesePhraseTag tag) {
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
			String sql = "DELETE FROM phrases_tags WHERE phrase_id = ? AND tag_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			 pStmt.setInt(1, tag.getPhraseId());
		     pStmt.setInt(2, tag.getTagId());

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