package scoremanager.main;

import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassListAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// セッションの取得
		HttpSession session = req.getSession();
		// ログインユーザー（講師）の情報をセッションから取得
		Teacher teacher = (Teacher) session.getAttribute("user");

		// クラス番号Daoの初期化
		ClassNumDao classNumDao = new ClassNumDao();

		// ログインユーザーの学校コードをもとにクラス番号の一覧（Stringのリスト）を取得
		// ※Daoの戻り値がList<String>であることを想定しています
		List<String> list = classNumDao.filter(teacher.getSchool());

		// レンスポンス値をリクエストにセット
		req.setAttribute("class_num_set", list);

		// クラス一覧JSPへフォワード
		req.getRequestDispatcher("class_list.jsp").forward(req, res);
	}
}