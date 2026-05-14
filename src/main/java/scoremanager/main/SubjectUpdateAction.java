package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String cd = req.getParameter("subject_cd");

        // 学校情報取得
        School school = teacher.getSchool();

        SubjectDao dao = new SubjectDao();

        // school を渡す
        
        Subject subject = dao.get(cd, school);

        if (subject == null) {
            req.getRequestDispatcher("subject_list.jsp").forward(req, res);
            return;
        }

        req.setAttribute("subject_cd", subject.getCd());
        req.setAttribute("susbject_name", subject.getName());

        req.getRequestDispatcher("subject_update.jsp").forward(req, res);
    }
}