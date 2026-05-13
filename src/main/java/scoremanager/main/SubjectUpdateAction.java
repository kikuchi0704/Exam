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

        String cd = req.getParameter("cd");

        // 学校情報取得
        School school = teacher.getSchool();

        SubjectDao dao = new SubjectDao();
<<<<<<< HEAD

        // school を渡す
        
=======
        Subject subject = dao.get(cd, school, teacher.getSchool());
>>>>>>> branch 'master' of https://github.com/kikuchi0704/Exam.git

        if (subject == null) {
            req.getRequestDispatcher("subject_list.jsp").forward(req, res);
            return;
        }

        req.setAttribute("cd", subject.getCd());
        req.setAttribute("name", subject.getName());

        req.getRequestDispatcher("subject_update.jsp").forward(req, res);
    }
}