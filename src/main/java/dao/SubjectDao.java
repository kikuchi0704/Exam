package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {
	
    public Subject get(String cd, School school) throws Exception {

    	Subject subject = new Subject();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement(
                "select * from subject where school_cd = ? and cd = ?"
            );

            statement.setString(1, school.getCd());
            statement.setString(2, cd);

            ResultSet rSet = statement.executeQuery();

            if (rSet.next()) {

                subject.setCd(rSet.getString("cd"));
                subject.setName(rSet.getString("name"));
                subject.setSchool(school);
            } else {
            	subject = null;
            }
        } catch (Exception e) {
        	throw e;
        } finally {
            if (statement != null) {
            	try {
            		statement.close();
            	} catch (SQLException sqle) {
            		throw sqle;
            	}
            }
            if (connection != null) {
            	try {
            		connection.close();
            	} catch (SQLException sqle) {
            		throw sqle;
            	}
            }
        }
        return subject;
    }

    // 科目一覧取得
    public List<Subject> filter(School school) throws Exception {

        List<Subject> list = new ArrayList<>();

        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {

        	statement = connection.prepareStatement("select cd, name from subject where school_cd = ?");

            statement.setString(1, school.getCd());

            ResultSet rSet = statement.executeQuery();

            while (rSet.next()) {
            	Subject subject = new Subject();
                subject.setCd(rSet.getString("cd"));
                subject.setName(rSet.getString("name"));
                subject.setSchool(school);
                list.add(subject);
            }
        } catch (Exception e) {
    		throw e;
        } finally {

            if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
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
    
    public boolean save(Subject subject) throws Exception {

		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// 実行件数
		int count = 0;

		try {
			// データベースから科目を取得
			Subject old = get(subject.getCd(), subject.getSchool());
			if (old == null) {
				// 科目が存在しなかった場合（追加）
				// プリペアードステートメントにINSERT文をセット
				statement = connection.prepareStatement(
						"insert into subject(school_cd, cd, name)values(?,?,?)"
						);
				// プリペアードステートメントに値をバインド
				statement.setString(1, subject.getSchool().getCd());
				statement.setString(2, subject.getCd());
				statement.setString(3, subject.getName());
				
			} else {
				// 科目が存在した場合（更新）
				// プリペアードステートメントにUPDATE文をセット
				statement=connection.prepareStatement(
						"update subject set name=? where cd=?"
						);
				
				// プリペアードステートメントに値をバインド
				statement.setString(1, subject.getName());
				statement.setString(2, subject.getCd());

			}
			// プリペアードステートメントを実行
			count = statement.executeUpdate();

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
    
    public boolean delete(Subject subject) throws Exception {
    	Connection connection = getConnection();
    	PreparedStatement statement = null;
    	int count = 0;

    	try {
    		// 科目コードと学校コードが一致するレコードを削除
    		statement = connection.prepareStatement(
    			"delete from subject where cd = ? and school_cd = ?"
    		);
    		statement.setString(1, subject.getCd());
    		statement.setString(2, subject.getSchool().getCd());

    		// 実行
    		count = statement.executeUpdate();
    	} catch (Exception e) {
    		throw e;
    	} finally {
    		if (statement != null) try { 
    			statement.close(); 
    		} catch (SQLException sqle) { 
    			throw sqle;
    			}
    		if (connection != null) try { 
    			connection.close(); 
    		} catch (SQLException sqle) { 
    			throw sqle;
    			}
    	}

    	return count > 0;
    }
}