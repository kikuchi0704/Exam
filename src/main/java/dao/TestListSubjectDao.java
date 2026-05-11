package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.TestListSubject;

/**
 * 科目別成績一覧DAO
 */
public class TestListSubjectDao extends Dao {

    public List<TestListSubject> filter(int entYear, String classNum, String subjectCd, School school) throws Exception {
        // 学生番号をキーにして、重複しないように学生データを保持するマップ
        Map<String, TestListSubject> map = new HashMap<>();
        Connection con = getConnection();
        PreparedStatement st = null;

        try {
            String sql = "SELECT S.ENT_YEAR, S.NO AS STUDENT_NO, S.NAME AS STUDENT_NAME, S.CLASS_NUM, T.NO AS TEST_COUNT, T.POINT " +
                         "FROM TEST T " +
                         "JOIN STUDENT S ON T.STUDENT_NO = S.NO AND T.SCHOOL_CD = S.SCHOOL_CD " +
                         "WHERE S.ENT_YEAR = ? AND S.CLASS_NUM = ? AND T.SUBJECT_CD = ? AND T.SCHOOL_CD = ? " +
                         "ORDER BY S.NO ASC, T.NO ASC";

            st = con.prepareStatement(sql);
            st.setInt(1, entYear);
            st.setString(2, classNum);
            st.setString(3, subjectCd);
            st.setString(4, school.getCd());

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String studentNo = rs.getString("STUDENT_NO");
                TestListSubject bean;

                if (map.containsKey(studentNo)) {
                    // すでにMapにある場合はそのインスタンスを取得
                    bean = map.get(studentNo);
                } else {
                    // 新規学生の場合は新しく作成して基本情報をセット
                    bean = new TestListSubject();
                    bean.setEntYear(rs.getInt("ENT_YEAR"));
                    bean.setStudentNo(studentNo);
                    bean.setStudentName(rs.getString("STUDENT_NAME"));
                    bean.setClassNum(rs.getString("CLASS_NUM"));
                    bean.setPoints(new HashMap<Integer, Integer>()); // Mapの初期化
                    map.put(studentNo, bean);
                }
                
                // 回数と点数をMapに追加
                bean.putPoint(rs.getInt("TEST_COUNT"), rs.getInt("POINT"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }

        // Mapの値をリストに変換して返す
        return new ArrayList<>(map.values());
    }
}