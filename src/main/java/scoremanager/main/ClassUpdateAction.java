package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class ClassUpdateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 変更対象のクラス番号を取得
		String oldClassNum = req.getParameter("class_num");

		// JSPに渡す
		req.setAttribute("oldClassNum", oldClassNum);

		req.getRequestDispatcher("class_update.jsp").forward(req, res);
	}
}