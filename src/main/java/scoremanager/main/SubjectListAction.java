package scoremanager.main;

import java.util.List;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectListAction extends Action {

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse res) throws Exception {

        // セッション取得
        HttpSession session = req.getSession();

        // ログイン中の先生取得
        Teacher teacher =
                (Teacher) session.getAttribute("user");

        // SubjectDao生成
        SubjectDao subjectDao = new SubjectDao();

        // 科目一覧取得
        List<Subject> subjects =
                subjectDao.filter(teacher.getSchool());

        // JSPへ渡す
        req.setAttribute("subjects", subjects);

        // JSPへフォワード
        req.getRequestDispatcher("subject_list.jsp")
           .forward(req, res);
    }
}