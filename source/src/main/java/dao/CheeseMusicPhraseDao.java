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
		public List<CheeseMusicPhrase> select(CheeseMusicPhrase card) {
			Connection conn = null;
			List<CheeseMusicPhrase> cardList = new ArrayList<CheeseMusicPhrase>();

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/music_cheese?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SQL文を準備する
				String sql = "SELECT number, post_code, company_name, address, department_name, telephone_number, position_name, fax_number, name, email_address, remarks FROM Bc WHERE company_name LIKE ? AND name LIKE ? ORDER BY number";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				if (card.getCompany_name() != null) {
					pStmt.setString(1, "%" + card.getCompany_name() + "%");
				} else {
					pStmt.setString(1, "%");
				}
				if (card.getName() != null) {
					pStmt.setString(2, "%" + card.getName() + "%");
				} else {
					pStmt.setString(2, "%");
				}

				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				while (rs.next()) {
					CheeseMusicPhrase = new CheeseMusicPhrase(rs.getInt("number"), 
													rs.getString("post_code"), 
													rs.getString("company_name"), 
													rs.getString("address"), 
													rs.getString("department_name"), 
													rs.getString("telephone_number"), 
													rs.getString("position_name"), 
													rs.getString("fax_number"), 
													rs.getString("name"), 
													rs.getString("email_address"), 
													rs.getString("remarks"));
					cardList.add(CheeseMusicPhrase);
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
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/music_cheese?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SQL文を準備する
				String sql = "INSERT INTO CheeseMusicPhrase VALUES (0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				if (card.getPost_code() != null) {
					pStmt.setString(1, card.getPost_code());
				} else {
					pStmt.setString(1, "");
				}
				if (card.getCompany_name() != null) {
					pStmt.setString(2, card.getCompany_name());
				} else {
					pStmt.setString(2, "");
				}
				if (card.getAddress() != null) {
					pStmt.setString(3, card.getAddress());
				} else {
					pStmt.setString(3, "");
				}
				if (card.getDepartment_name() != null) {
					pStmt.setString(4, card.getDepartment_name());
				} else {
					pStmt.setString(4, "");
				}
				if (card.getTelephone_number() != null) {
					pStmt.setString(5, card.getTelephone_number());
				} else {
					pStmt.setString(5, "");
				}
				if (card.getPosition_name() != null) {
					pStmt.setString(6, card.getPosition_name());
				} else {
					pStmt.setString(6, "");
				}
				if (card.getFax_number() != null) {
					pStmt.setString(7, card.getFax_number());
				} else {
					pStmt.setString(7, "");
				}
				if (card.getName() != null) {
					pStmt.setString(8, card.getName());
				} else {
					pStmt.setString(8, "");
				}
				if (card.getEmail_address() != null) {
					pStmt.setString(9, card.getEmail_address());
				} else {
					pStmt.setString(9, "");
				}
				if (card.getRemarks() != null) {
					pStmt.setString(10, card.getRemarks());
				} else {
					pStmt.setString(10, "");
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

		// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
		public boolean update(CheeseMusicPhrase card) {
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
				String sql = "UPDATE Bc SET Post_code=?, company_name=?, address=?, department_name=?, telephone_number=?, position_name=?, fax_number=?, name=?, email_address=?, remarks=? WHERE number=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				if (card.getPost_code() != null) {
					pStmt.setString(1, card.getPost_code());
				} else {
					pStmt.setString(1, "");
				}
				if (card.getCompany_name() != null) {
					pStmt.setString(2, card.getCompany_name());
				} else {
					pStmt.setString(2, "");
				}
				if (card.getAddress() != null) {
					pStmt.setString(3, card.getAddress());
				} else {
					pStmt.setString(3, "");
				}
				if (card.getDepartment_name() != null) {
					pStmt.setString(4, card.getDepartment_name());
				} else {
					pStmt.setString(4, "");
				}
				if (card.getTelephone_number() != null) {
					pStmt.setString(5, card.getTelephone_number());
				} else {
					pStmt.setString(5, "");
				}
				if (card.getPosition_name() != null) {
					pStmt.setString(6, card.getPosition_name());
				} else {
					pStmt.setString(6, "");
				}
				if (card.getFax_number() != null) {
					pStmt.setString(7, card.getFax_number());
				} else {
					pStmt.setString(7, "");
				}
				if (card.getName() != null) {
					pStmt.setString(8, card.getName());
				} else {
					pStmt.setString(8, "");
				}
				if (card.getEmail_address() != null) {
					pStmt.setString(9, card.getEmail_address());
				} else {
					pStmt.setString(9, "");
				}
				if (card.getRemarks() != null) {
					pStmt.setString(10, card.getRemarks());
				} else {
					pStmt.setString(10, "");
				}
				
				pStmt.setInt(11, card.getNumber());

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
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/music_cheese?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SQL文を準備する
				String sql = "DELETE FROM Bc WHERE number=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setInt(1, card.getNumber());

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
