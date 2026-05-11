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

/**
 * 学生別成績一覧表示の実行アクション
 */
public class TestListStudentExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションからユーザー情報を取得（学校コードの特定に使用）
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // リクエストパラメータから学生番号を取得
        String studentNo = req.getParameter("f4");

        // DAOの初期化
        TestListStudentDao tDao = new TestListStudentDao();
        StudentDao sDao = new StudentDao();

        // 1. 学生情報を取得（氏名表示用）
        Student student = sDao.get(studentNo);

        // 2. その学生の成績一覧を取得
        List<TestListStudent> tests = tDao.filter(student, teacher.getSchool());

        // JSPに渡すデータをリクエスト属性にセット
        req.setAttribute("f4", studentNo); // 入力値を保持
        req.setAttribute("student", student); // 学生情報（氏名など）
        req.setAttribute("tests", tests); // 成績リスト

        // 学生別成績一覧JSPへフォワード
        req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
    }
}