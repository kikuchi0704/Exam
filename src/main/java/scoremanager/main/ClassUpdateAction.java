package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class ClassUpdateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 一覧画面のリンクから送られてきた「変更対象のクラス番号」を取得
		String oldClassNum = req.getParameter("class_num");

		// 変更画面の入力フォームで初期値として表示するため、および隠しパラメータ用としてリクエストにセット
		req.setAttribute("oldClassNum", oldClassNum);

		// クラス変更入力画面（class_update.jsp）へフォワード
		req.getRequestDispatcher("class_update.jsp").forward(req, res);
	}
}