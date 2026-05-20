package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassUpdateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");

		String oldClassNum = req.getParameter("oldClassNum");
		String newClassNum = req.getParameter("classNum");

		ClassNumDao classNumDao = new ClassNumDao();
		Map<String, String> errors = new HashMap<>();

		// 1. 重複チェック
		List<String> list = classNumDao.filter(teacher.getSchool());
		if (list.contains(newClassNum) && !newClassNum.equals(oldClassNum)) {
			errors.put("classNum", "クラス番号が重複しています");
		}

		// エラーがあれば入力画面へ戻す
		if (!errors.isEmpty()) {
			req.setAttribute("errors", errors);
			req.setAttribute("oldClassNum", oldClassNum);
			req.setAttribute("classNum", newClassNum);
			req.getRequestDispatcher("class_update.jsp").forward(req, res);
			return;
		}

		// 2. 更新処理（Daoの仕様に合わせる）
		// まず、変更対象となる「元のクラス」の ClassNum インスタンスをDBから取得する
		ClassNum oldClass = classNumDao.get(oldClassNum, teacher.getSchool());

		if (oldClass != null) {
			// 取得したインスタンスと、新しいクラス番号を引数に渡して save (更新) メソッドを実行
			boolean isSuccess = classNumDao.save(oldClass, newClassNum);

			if (isSuccess) {
				// 成功時
				req.getRequestDispatcher("class_update_done.jsp").forward(req, res);
			} else {
				// 失敗時（Dao側で count > 0 にならなかった場合）
				errors.put("forced", "変更処理に失敗しました。時間をおいて再度お試しください。");
				req.setAttribute("errors", errors);
				req.setAttribute("oldClassNum", oldClassNum);
				req.getRequestDispatcher("class_update.jsp").forward(req, res);
			}
		} else {
			// 万が一、対象のクラスがすでに削除されている等の理由で取得できなかった場合
			errors.put("forced", "対象のクラスが存在しません。");
			req.setAttribute("errors", errors);
			req.setAttribute("oldClassNum", oldClassNum);
			req.getRequestDispatcher("class_update.jsp").forward(req, res);
		}
	}
}