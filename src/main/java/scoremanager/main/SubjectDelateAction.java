package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDelateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// セッションからユーザー情報を取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		// リクエストパラメーターの取得（削除対象の科目コード）
		String cd = req.getParameter("cd");

		// DBからデータ取得
		SubjectDao sDao = new SubjectDao();
		// 科目コードと学校コードから科目インスタンスを取得
		Subject subject = sDao.get(cd, teacher.getSchool());

		// レスポンス値をセット
		req.setAttribute("subject", subject);

		// JSPへフォワード
		req.getRequestDispatcher("subject_delete.jsp").forward(req, res);
	}
}
