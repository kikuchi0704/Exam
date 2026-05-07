package scoremanager.main;

import java.util.List;

import bean.Teacher;
import bean.TestListSubject;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

/**
 * 科目別成績一覧表示の実行アクション
 */
public class TestListSubjectExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションから教師情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // リクエストパラメータの取得（f1:入学年度, f2:クラス, f3:科目コード）
        int entYear = Integer.parseInt(req.getParameter("f1"));
        String classNum = req.getParameter("f2");
        String subjectCd = req.getParameter("f3");

        // 未選択チェック（バリデーション）
        if (entYear == 0 || classNum.equals("0") || subjectCd.equals("0")) {
            req.setAttribute("errors", "入学年度、クラス、科目を選択してください");
            req.getRequestDispatcher("TestList.action").forward(req, res);
            return;
        }

        // DAOを使ってデータを取得
        TestListSubjectDao dao = new TestListSubjectDao();
        List<TestListSubject> tests = dao.filter(entYear, classNum, subjectCd, teacher.getSchool());

        // JSPで表示するためにリクエスト属性へセット
        req.setAttribute("f1", entYear);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", subjectCd);
        req.setAttribute("tests", tests);

        // 科目別成績一覧JSPへフォワード
        req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
    }
}