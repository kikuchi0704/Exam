package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッション取得 1
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // ローカル変数の指定 2
        SubjectDao subjectDao = new SubjectDao();

        // リクエストパラメータの取得 3
        String subjectCd = req.getParameter("subject_cd");
        String subjectName = req.getParameter("subject_name");

        // ビジネスロジック 4
        Subject subject = new Subject();
        subject.setCd(subjectCd);
        subject.setName(subjectName);
        subject.setSchool(teacher.getSchool());

        // DBへ登録 5
        subjectDao.save(subject);

        // レスポンス値をセット 6
        // （今回は完了画面なので特になし）

        // JSPへフォワード 7
        req.getRequestDispatcher("subject_create_done.jsp")
           .forward(req, res);
    }
}