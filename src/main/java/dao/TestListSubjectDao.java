package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
        List<TestListSubject> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        // 【解説】LEFT JOINを使って、点数がない学生もリストに載るようにします
        // order by st.no で並べることで、同じ学生の「1回目」「2回目」のデータが連続して現れるようにします
        String sql = "select st.ent_year, st.class_num, st.no as student_no, st.name, t.no as test_no, t.point " +
                     "from student st " +
                     "left join test t on st.no = t.student_no and t.subject_cd = ? and t.school_cd = st.school_cd " +
                     "where st.ent_year = ? and st.class_num = ? and st.school_cd = ? " +
                     "order by st.no asc, t.no asc";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, subject.getCd());
            statement.setInt(2, entYear);
            statement.setString(3, classNum);
            statement.setString(4, school.getCd());
            rSet = statement.executeQuery();

            String lastStudentNo = ""; // 1つ前の行の学生番号を覚えておく
            TestListSubject tls = null;

            while (rSet.next()) {
            	String currentStudentNo = rSet.getString("student_no");
                int testNo = rSet.getInt("test_no");
                int point = rSet.getInt("point");

                // 【解説】新しい学生番号が現れたら、新しい「1人分の入れ物(Bean)」を作る
                if (!currentStudentNo.equals(lastStudentNo)) {
                    tls = new TestListSubject();
                    tls.setEntYear(rSet.getInt("ent_year"));
                    tls.setClassNum(rSet.getString("class_num"));
                    tls.setStudentNo(currentStudentNo);
                    tls.setStudentName(rSet.getString("name"));
                    
                    // Mapを初期化（これがないとput時にエラーになる）
                    tls.setPoints(new HashMap<Integer, Integer>());
                    
                    list.add(tls);
                    lastStudentNo = currentStudentNo; // 今の番号を「1つ前」として記録
                }

                // 【解説】テストの回数（1または2）と点数を取得
                //int testNo = rSet.getInt("test_no");
                if (!rSet.wasNull()) {
                    // testNo(1または2)をキーにして、点数をMapに保存する
                    // 同じ学生の2行目が来たときは、同じtlsオブジェクトのMapに2つ目のデータが入る
                    tls.putPoint(testNo, rSet.getInt("point"));
                }
            }
        } finally {
            if (rSet != null) rSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return list;
    }
}