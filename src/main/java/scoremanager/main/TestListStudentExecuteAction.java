package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.StudentDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 入力された学生番号を取得
        String studentNo = req.getParameter("f4");

        // 既存のStudentDaoを使用して学生情報を取得
        StudentDao sDao = new StudentDao();
        Student student = sDao.get(studentNo);

        if (student != null) {
            // 学生が存在する場合、成績リストを取得
            TestListStudentDao tDao = new TestListStudentDao();
            List<TestListStudent> tests = tDao.filter(student, teacher.getSchool());
            
            req.setAttribute("student", student);
            req.setAttribute("tests", tests);
        } else {
            // 学生が見つからない場合のエラー処理
            req.setAttribute("errors", "学生情報が存在しませんでした");
        }

        // 学生別成績一覧画面へ
        req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
    }
}