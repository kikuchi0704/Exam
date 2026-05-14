package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 1. 共通情報を取得
        String subjectCode = request.getParameter("subject_cd");
        int entYear = Integer.parseInt(request.getParameter("ent_year"));
        int num = Integer.parseInt(request.getParameter("count"));
        String classNum = request.getParameter("class_num");

        // 2. 学生リストを取得してループ処理
        String[] studentNos = request.getParameterValues("student_no");
        
        if (studentNos != null) {
            TestDao tDao = new TestDao();
            Subject subject = new Subject();
            subject.setCd(subjectCode);

            // 1. ループの外でコネクションを1つだけ取得する
            java.sql.Connection connection = tDao.getConnection(); 

            try {
                for (String studentNo : studentNos) {
                    String pointStr = request.getParameter("point_" + studentNo);
                    int point = Integer.parseInt(pointStr);

                    Test test = new Test();
                    bean.Student student = new bean.Student();
                    student.setNo(studentNo); // 画面から受け取った学籍番号をセット
                    
                    test.setStudent(student); // Testに学生をセット
                    test.setSubject(subject);
                    test.setSchool(teacher.getSchool());
                    test.setNo(num);
                    test.setPoint(point);
                    test.setClassNum(classNum);

                    // 3. DAOに渡す（第2引数にconnectionを忘れずに）
                    tDao.save(test, connection); 
                }
            } finally {
                // 3. すべての処理が終わったらコネクションを閉じる
                if (connection != null) {
                    connection.close();
                }
            }
        }

        // 3. 完了画面へ
        response.sendRedirect("test_regist_done.jsp");
    }
}