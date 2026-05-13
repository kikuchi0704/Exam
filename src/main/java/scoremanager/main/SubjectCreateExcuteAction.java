package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExcuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String subjectCd = "";
        String subjectName = "";

        Subject subject = new Subject();
        SubjectDao subjectDao = new SubjectDao();

        Map<String, String> errors = new HashMap<>();

        // 入力取得
        subjectCd = req.getParameter("subject_cd");
        subjectName = req.getParameter("subject_name");

        // エラーチェック
        if (subjectCd == null || subjectCd.isEmpty()) {
            errors.put("1", "科目コードを入力してください");
        }

        if (subjectName == null || subjectName.isEmpty()) {
            errors.put("2", "科目名を入力してください");
        }

        // 重複チェック
        if (subjectDao.get(subjectCd, teacher.getSchool()) != null) {
            errors.put("3", "科目コードが重複しています");
        }

        // エラー処理
        if (!errors.isEmpty()) {

            req.setAttribute("errors", errors);
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);
            return;
        }

        // 登録データ作成
        subject.setCd(subjectCd);
        subject.setName(subjectName);
        subject.setSchool(teacher.getSchool());

        // DB登録
        subjectDao.save(subject);

        // 完了画面へ
        req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
    }
}