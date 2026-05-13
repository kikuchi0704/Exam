package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {

    public List<TestListStudent> filter(Student student, School school) throws Exception {
        List<TestListStudent> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        // 科目名を表示するため、testとsubjectを結合
        String sql = "select sub.name as subject_name, sub.cd as subject_cd, t.no, t.point " +
                     "from test t " +
                     "join subject sub on t.subject_cd = sub.cd and t.school_cd = sub.school_cd " +
                     "where t.student_no = ? and t.school_cd = ? " +
                     "order by sub.cd asc, t.no asc";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, student.getNo());
            statement.setString(2, school.getCd());
            rSet = statement.executeQuery();

            while (rSet.next()) {
                TestListStudent res = new TestListStudent();
                res.setSubjectName(rSet.getString("subject_name"));
                res.setSubjectCd(rSet.getString("subject_cd"));
                res.setNum(rSet.getInt("no"));
                res.setPoint(rSet.getInt("point"));
                list.add(res);
            }
        } finally {
            if (rSet != null) rSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return list;
    }
}