package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDelateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// セッションからユーザー情報を取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		// リクエストパラメーターの取得
		String cd = req.getParameter("cd");

		// ビジネスロジック（削除処理）
		SubjectDao sDao = new SubjectDao();
		// まず削除対象の存在確認
		Subject subject = sDao.get(cd, teacher.getSchool());

		if (subject != null) {
			// 対象が存在する場合のみ削除を実行
			sDao.delete(subject);
		}

		// JSPへフォワード（削除完了画面、または科目一覧へリダイレクト）
		req.getRequestDispatcher("subject_delete_done.jsp").forward(req, res);
	}
}