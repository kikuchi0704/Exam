package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {

    private String baseSql =
            "select * from subject where school_cd = ?";

    // 科目一覧取得
    public List<Subject> filter(School school) throws Exception {

        List<Subject> list = new ArrayList<>();

        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            statement = connection.prepareStatement(
                    baseSql + " order by cd asc"
            );

            statement.setString(1, school.getCd());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Subject subject = new Subject();

                subject.setCd(resultSet.getString("cd"));
                subject.setName(resultSet.getString("name"));
                subject.setSchool(school);

                list.add(subject);
            }

        } finally {

            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return list;
    }

    // ★追加：更新処理
    public boolean update(Subject subject) throws Exception {

        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement(
                "update subject set name = ? where cd = ? and school_cd = ?"
            );

            statement.setString(1, subject.getName());
            statement.setString(2, subject.getCd());
            statement.setString(3, subject.getSchool().getCd());

            int count = statement.executeUpdate();

            return count > 0;

        } finally {

            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }
    public Subject get(String cd) throws Exception {

        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            statement = connection.prepareStatement(
                "select * from subject where cd = ?"
            );

            statement.setString(1, cd);

            resultSet = statement.executeQuery();

            Subject subject = null;

            if (resultSet.next()) {

                subject = new Subject();

                subject.setCd(resultSet.getString("cd"));
                subject.setName(resultSet.getString("name"));
            }

            return subject;

        } finally {

            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }
}