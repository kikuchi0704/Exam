package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.TestListStudent;

/**
 * 学生別成績一覧DAO
 */
public class TestListStudentDao extends Dao {

    /**
     * 学生と学校を指定して、成績一覧を取得する
     * @param student 学生Bean
     * @param school 学校Bean
     * @return TestListStudentのリスト
     * @throws Exception
     */
    public List<TestListStudent> filter(Student student, School school) throws Exception {
        List<TestListStudent> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement st = null;

        try {
            // SQL文：科目テーブルと結合して科目名を取得、学生番号で絞り込み
            // 成績回数、科目コードの順でソート
            String sql = "SELECT SUB.NAME AS SUBJECT_NAME, T.SUBJECT_CD, T.NO, T.POINT " +
                         "FROM TEST T " +
                         "JOIN SUBJECT SUB ON T.SUBJECT_CD = SUB.CD AND T.SCHOOL_CD = SUB.SCHOOL_CD " +
                         "WHERE T.STUDENT_NO = ? AND T.SCHOOL_CD = ? " +
                         "ORDER BY T.SUBJECT_CD ASC, T.NO ASC";

            st = con.prepareStatement(sql);
            st.setString(1, student.getNo());
            st.setString(2, school.getCd());

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                TestListStudent bean = new TestListStudent();
                bean.setSubjectName(rs.getString("SUBJECT_NAME"));
                bean.setSubjectCd(rs.getString("SUBJECT_CD"));
                bean.setNum(rs.getInt("NO")); // SQLの回数を num にセット
                bean.setPoint(rs.getInt("POINT"));
                list.add(bean);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }

        return list;
    }
}