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
	
	// 引数cardで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(CheeseMusic card) {
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
		String sql = "INSERT INTO musics(name, user_id) VALUES (?, ?)";
		PreparedStatement pStmt = conn.prepareStatement(sql);
					
		// SQL文を完成させる
		if (card.getName() != null) {
			pStmt.setString(1,card.getName());
			} else {
			pStmt.setString(1, "");
			}
		if (card.getUserId() != 0) {
			pStmt.setInt(2, card.getUserId() );
			} else {
			pStmt.setInt(2, 0);
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

				// 結果を返す
				return result;
		}
	
	// 引数card指定された項目で検索して、取得されたデータのリストを返す
	public List<CheeseMusic> select(CheeseMusic card) {
		Connection conn = null;
		List<CheeseMusic> cardList = new ArrayList<CheeseMusic>();
		
		try {
		// JDBCドライバを読み込む
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b5?"
							+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
							"root", "password");

		// SQL文を準備する
		String sql = "SELECT id, name, user_id, created_at, updated_at "
					+ "FROM musics "
					+ "WHERE name LIKE ? AND (? = 0 OR user_id = ?) "
					+ "ORDER BY created_at";
					
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		// SQL文を完成させる
		if (card.getName() != null) {
			pStmt.setString(1, "%" + card.getName() + "%");
		} else {
			pStmt.setString(1, "%");
		}
		pStmt.setInt(2, card.getUserId());
		pStmt.setInt(3, card.getUserId());
		
		// SQL文を実行し、結果表を取得する
		ResultSet rs = pStmt.executeQuery();

		// 結果表をコレクションにコピーする
		while (rs.next()) {
			CheeseMusic musics = new CheeseMusic(
					rs.getInt("id"), 
					rs.getString("name"),
					rs.getInt("user_id"),
					rs.getString("created_at"),
					rs.getString("updated_at")
					);
			cardList.add(musics);
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
	
	//検索結果の並び替え処理
	public List<CheeseMusic> select(CheeseMusic card, String orderBy) {
	    Connection conn = null;
	    List<CheeseMusic> cardList = new ArrayList<>();
	    
	    String safeOrderBy;
	    switch (orderBy) {
	        case "new":  safeOrderBy = "created_at DESC"; break;
	        case "old":  safeOrderBy = "created_at ASC"; break;
	        case "update": safeOrderBy = "updated_at DESC"; break;
	        default:     safeOrderBy = "created_at DESC"; break;
	    } 
	    
	    try {
	    	// JDBCドライバを読み込む
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        // データベースに接続する
	        conn = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/b5?"
	            + "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
	            "root", "password"
	        		);
	        
	        // SQL文を準備する    
	        String sql = "SELECT id, name, user_id, created_at, updated_at "
	        			+ "FROM musics "
	        			+ "WHERE name LIKE ? "
	        			+ "ORDER BY " + safeOrderBy;
	        PreparedStatement pStmt = conn.prepareStatement(sql);
	        
	        // SQL文を完成させる
	        pStmt.setString(1, "%" + (card.getName() != null ? card.getName() : "") + "%");
        
	        ResultSet rs = pStmt.executeQuery();
	        
	        // SQL文を実行する
	        while (rs.next()) {
            CheeseMusic musics = new CheeseMusic(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("user_id"),
                rs.getString("created_at"),
                rs.getString("updated_at")
            );
            cardList.add(musics);
        	}   
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        cardList = null;
	    } finally {
	        try {
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return cardList;
	}        
	
	// 引数cardで指定された番号のレコードを削除し、成功したらtrueを返す
		public boolean delete(CheeseMusic card) {
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
				String sql = "DELETE FROM musics WHERE id=?";
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
