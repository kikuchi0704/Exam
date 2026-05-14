package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

    public Test get(Student student, Subject subject, School school, int no) throws Exception {
        
    	// 得点インスタンスを初期化
    	Test test = new Test();
    	// データベースへのコネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        
        try {
            statement = connection.prepareStatement(
                    "SELECT * FROM test WHERE student_no = ? AND subject_cd = ? AND school_cd = ? AND no = ?"
                );
            
            // プリペアードステートメントに学生番号をバインド
            statement.setString(1, student.getNo());
            statement.setString(2, subject.getCd());
            statement.setString(3, school.getCd());
            statement.setInt(4, no);
            
            // プリペアードステートメントを実行
            ResultSet rSet = statement.executeQuery();

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
        } finally {
        	// プリペアードステートメントを閉じる
            if (statement != null) {
                try { statement.close(); } catch (SQLException sqle) { throw sqle; }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException sqle) { throw sqle; }
            }
        }
        return test;
    }
    
    private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                Test test = new Test();
                
                // 学生情報のセット
                Student student = new Student();
                student.setNo(rSet.getString("student_no"));
                
                try {
                    student.setName(rSet.getString("student_name"));
                } catch (SQLException e) {
                    // 名前カラムがない場合は単にスルー（またはログ出力）
                }

                // 科目情報のセット
                Subject subject = new Subject();
                subject.setCd(rSet.getString("subject_cd"));

                // テスト情報のセット
                test.setStudent(student);
                test.setSubject(subject);
                test.setSchool(school);
                test.setNo(rSet.getInt("no"));
                test.setPoint(rSet.getInt("point"));
                test.setClassNum(rSet.getString("class_num"));
                
                list.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return list;
    }
    
    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            // 「指定した条件の学生」と「その学生の特定のテスト成績」を結合して取得
            // 成績がない学生も表示するため LEFT OUTER JOIN を使うのが一般的です
            String sql = "SELECT s.no, s.name, s.ent_year, s.class_num, t.point " +
                         "FROM student s " +
                         "LEFT OUTER JOIN test t ON s.no = t.student_no " +
                         "AND t.subject_cd = ? AND t.no = ? " +
                         "WHERE s.ent_year = ? AND s.class_num = ? AND s.school_cd = ?";
            
            statement = connection.prepareStatement(sql);
            statement.setString(1, subject.getCd());
            statement.setInt(2, num);
            statement.setInt(3, entYear);
            statement.setString(4, classNum);
            statement.setString(5, school.getCd());

            ResultSet rSet = statement.executeQuery();
            while (rSet.next()) {
                Test test = new Test();
                Student student = new Student();
                student.setNo(rSet.getString("no"));
                student.setName(rSet.getString("name"));
                student.setEntYear(rSet.getInt("ent_year"));
                student.setClassNum(rSet.getString("class_num"));
                
                test.setStudent(student);
                test.setPoint(rSet.getInt("point")); // 成績がなければ0（またはnull管理）
                test.setNo(num);
                test.setSubject(subject);
                test.setSchool(school);
                list.add(test);
            }
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return list;
    }

    public void save(List<Test> tests) throws Exception {
        Connection connection = getConnection();
        try {
            // オートコミットをオフにしてトランザクション開始（一括処理のため）
            connection.setAutoCommit(false);
            
            for (Test test : tests) {
                // 提供された private save メソッドを呼び出す
                save(test, connection);
            }
            
            connection.commit(); // すべて成功したら確定
        } catch (Exception e) {
            connection.rollback(); // 失敗したら元に戻す
            throw e;
        } finally {
            connection.close();
        }
    }

    public boolean save(Test test, Connection connection) throws Exception {
    	PreparedStatement statement = null;
        int count = 0;
        try {
            boolean exists = false;
            String checkSql = "SELECT COUNT(*) FROM test WHERE student_no = ? AND subject_cd = ? AND school_cd = ? AND no = ?";
            
            // 引数の connection を使ってチェック
            try (PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
                checkStmt.setString(1, test.getStudent().getNo());
                checkStmt.setString(2, test.getSubject().getCd());
                checkStmt.setString(3, test.getSchool().getCd());
                checkStmt.setInt(4, test.getNo());
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    exists = true;
                }
            }

            if (!exists) {
                // 引数の connection を使って prepareStatement する
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
                // ここも同様に引数の connection を使う
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
            if (statement != null) {
                statement.close();
            }
            // ここで connection.close() はしない（Action側で閉じるため）
        }
        return count > 0;
    }
    
    
    
}