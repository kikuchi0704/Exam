package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // JSPからのパラメータ取得
        String cd = req.getParameter("subject_cd");
        String name = req.getParameter("subject_name");

        // Beanにセット
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());

        SubjectDao dao = new SubjectDao();

        dao.save(subject);
        
        
        // ★更新後は一覧へ戻すのが正しい
        req.getRequestDispatcher("SubjectList.action").forward(req, res);

        // DAOのupdateメソッドを呼び出す
        boolean isSuccess = dao.update(subject);

        if (isSuccess) {
            // 更新成功：完了画面へ（画像2）
            req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
        } else {
            // 更新失敗（他画面で削除された場合など）：画像3のエラー表示
            req.setAttribute("errors", "科目が存在しません");
            // 入力値を保持して修正画面へ戻す
            req.setAttribute("subject_cd", cd);
            req.setAttribute("subject_name", name);
            req.getRequestDispatcher("subject_update.jsp").forward(req, res);
        }

    }
}