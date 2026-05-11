package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

import bean.School;
import bean.Student;
import bean.Test;

public class TestDao extends Dao {

	private String baseSql = "select * from test where school_cd = ?";
	
	public Test get(Student student, Subject subject, School school, int no) throws Exception {
		// 得点インスタンスを初期化
		Test test = new Test();
		// データベースへのコネクションを確率
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		
		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(
					"SELECT * FROM test WHERE student_no = ? AND subject_cd = ? AND school_cd = ? AND no = ?"
				);
			// プリペアードステートメントに値をバインドs
			statement.setString(1, student.getNo());
			statement.setString(2, subject.getCd());
			statement.setString(3, school.getCd());
			statement.setInt(4, no);
			// プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();			
			// 得点Daoを初期化
			TestDao testDao = new TestDao();

			if (rSet.next()) {
				// リザルトセットが存在する場合
				// 得点インスタンスに検索結果をセット
				test.setStudent(student);
				test.setSubject(subject);
				test.setSchool(school);
				test.setNo(rSet.getInt("no"));
				test.setPoint(rSet.getInt("point"));
				test.setClassNum(rSet.getString("class_num"));
				
			} else {
				// リザルトセットが存在しない場合
				// 得点インスタンスにnullをセット
				test = null;
			}
		} catch (Exception e) {
			throw e;
		}finally {
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
		
		return test;
	}
	
	private List<Test> postFilter(ResultSet rSet, School school) throws Exception {

		// リストを初期化
		List<Test> list = new ArrayList<>();
		try {
			// リザルトセットを全権走査
			while (rSet.next()) {
				// 学生インスタンスを初期化
				Test test = new Test();
				// 学生インスタンスに検索結果をセット
				test.setStudent(rSet.getString("student_no"));
				test.setSubject(rSet.getString("subject_cd"));
				test.setSchool(school);
				test.setNo(rSet.getInt("no"));
				test.setPoint(rSet.getInt("point"));
				test.setClassNum(rSet.getString("class_num"));
				
				// リストに追加
				list.add(test);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {

		// リストを初期化
		List<Test> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;
		// SQL文の条件
		String sql = "SELECT t.*, s.name AS student_name FROM test t JOIN student s ON t.student_no = s.no "
				+ "WHERE s.ent_year = ? AND s.class_num = ? AND t.subject_cd = ? AND t.no = ? AND t.school_cd = ?";

		try {
			// プリペアードステートメントにSQL文をセット
			statement=connection.prepareStatement("basesql" + sql);
			
			// プリペアードステートメントに学校コードをバインド
			statement.setInt(1, entYear);
			statement.setString(2, classNum);
			statement.setString(3, subject.getCd());
			statement.setInt(4, num);
			statement.setString(5, school.getCd());
			// プリペアードステートメント(sql)を実行
			rSet = statement.executeQuery();
			// リストへの格納処理を実行
			list=postFilter(rSet, school);
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

	public boolean save(List<Test> list) throws Exception {
	    Connection connection = getConnection();
	    boolean result = true;

	    try {
	        // オートコミットをオフにする（一括処理の整合性を保つため）
	        connection.setAutoCommit(false);

	        for (Test test : list) {
	            // 下記の private 側の save メソッドを呼び出す
	            boolean isSuccess = save(test, connection);
	            if (!isSuccess) {
	                result = false;
	                break; // 1件でも失敗したら中断
	            }
	        }

	        if (result) {
	            connection.commit(); // すべて成功なら確定
	        } else {
	            connection.rollback(); // 失敗があればすべて取り消し
	        }
	    } catch (Exception e) {
	        if (connection != null) connection.rollback();
	        throw e;
	    } finally {
	        if (connection != null) {
	            connection.setAutoCommit(true);
	            connection.close();
	        }
	    }
	    return result;
	}

	/**
	 * 1件ごとの保存（登録または更新）を行う
	 * ※このメソッド内では connection.close() をしないのがポイント！
	 */
	private boolean save(Test test, Connection connection) throws Exception {
	    PreparedStatement statement = null;
	    int count = 0;

	    try {
	        // すでに存在するか主キーでチェック
	        // get(student, subject, school, no) を使用
	        Test old = get(test.getStudent(), test.getSubject(), test.getSchool(), test.getNo());

	        if (old == null) {
	            // 登録
	            statement = connection.prepareStatement(
	                "insert into test(student_no, subject_cd, school_cd, no, point, class_num) values(?,?,?,?,?,?)"
	            );
	            statement.setString(1, test.getStudent().getNo());
	            statement.setString(2, test.getSubject().getCd());
	            statement.setString(3, test.getSchool().getCd());
	            statement.setInt(4, test.getNo());
	            statement.setInt(5, test.getPoint());
	            statement.setString(6, test.getClassNum());
	        } else {
	            // 更新
	            statement = connection.prepareStatement(
	                "update test set point=?, class_num=? where student_no=? and subject_cd=? and school_cd=? and no=?"
	            );
	            statement.setInt(1, test.getPoint());
	            statement.setString(2, test.getClassNum());
	            statement.setString(3, test.getStudent().getNo());
	            statement.setString(4, test.getSubject().getCd());
	            statement.setString(5, test.getSchool().getCd());
	            statement.setInt(6, test.getNo());
	        }
	        count = statement.executeUpdate();
	    } finally {
	        if (statement != null) statement.close();
	    }
	    return count > 0;
	}
	
}
