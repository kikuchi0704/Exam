package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;


public class ClassNumDao extends Dao {

	public ClassNum get(String class_num, School school) throws Exception {

		// クラス番号インスタンスを初期化
		ClassNum classNum = new ClassNum();
		// データベースへのコネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(
					"SELECT * FROM class_num WHERE class_num = ? AND school_cd = ?"
				);
			// プリペアードステートメントに値をバインド
			statement.setString(1, class_num);
			statement.setString(2, school.getCd());
			// プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();			
			// 学校Daoを初期化
			SchoolDao schoolDao = new SchoolDao();

			if (rSet.next()) {
				// リザルトセットが存在する場合
				// クラス番号インスタンスに検索結果をセット
				classNum.setClass_num(rSet.getString("class_num"));
				classNum.setSchool(school);
			} else {
				// リザルトセットが存在しない場合
				// クラス番号インスタンスにnullをセット
				classNum = null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return classNum;
	}

	/**
	 * filterメソッド 学校を指定してクラス番号の一覧を取得する
	 *
	 * @param school:School
	 * @return クラス番号の一覧:List<String>
	 * @throws Exception
	 */
	public List<String> filter(School school) throws Exception {
		// リストを初期化
		List<String> list = new ArrayList<>();
		// データベースへのコネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("SELECT class_num FROM class_num WHERE school_cd = ?");
			// プリペアードステートメントに学校コードをバインド
			// ?1個目に「school.getCd()」の値をセット
			// 「school.getCd()」のschoolは引数のschool
			statement.setString(1, school.getCd());
			// プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();

			// リザルトセットを全件走査
			while (rSet.next()) {
				ClassNum classNum = new ClassNum();
				classNum.setSchool(school);
				classNum.setClass_num(rSet.getString("class_num"));
				// リストにクラス番号を追加
				list.add(classNum);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return list;
	}

	/**
	 * 登録用のsaveメソッド
	 * @param classNum
	 * @return 実行可否
	 * @throws Exception
	 */
	public boolean save(ClassNum classNum) throws Exception {

		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// 実行件数
		int count = 0;

		try {
			// プリペアードステートメントにINSERT文をセット

			// プリペアードステートメントに値をバインド

					
			// プリペアードステートメントを実行
			
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		if (count > 0) {
			// 実行件数が1件以上ある場合
			return true;
		} else {
			// 実行件数が0件の場合
			return false;
		}
	}

	/**
	 * 変更用saveメソッド
	 * @param classNum
	 * @param newClassNum
	 * @return 変更可否
	 * @throws Exception
	 */
	public boolean save(ClassNum classNum, String newClassNum) throws Exception {

		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// 実行件数
		int count = 0;

		try {
			// プリペアードステートメントにUPDATE文をセット

			
			
			// プリペアードステートメントを実行

			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}

			// 登録されている学生のクラスも変更

			
			
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}

			// テストに登録されているクラスも変更

			
			
			
			
			
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		if (count > 3) {
			// 実行件数が3件以上ある場合
			return true;
		} else {
			// 実行件数が3件未満の場合
			return false;
		}
	}

}