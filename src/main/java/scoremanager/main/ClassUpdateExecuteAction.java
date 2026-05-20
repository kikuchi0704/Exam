package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassUpdateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// セッションから教員情報を取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");

		// リクエストパラメータ（元のクラス番号と、新しく入力されたクラス番号）の取得
		String oldClassNum = req.getParameter("oldClassNum");
		String newClassNum = req.getParameter("classNum");

		ClassNumDao classNumDao = new ClassNumDao();
		Map<String, String> errors = new HashMap<>();

		// ビジネスロジック：同一の学校内でクラス番号が重複していないかチェック
		List<String> list = classNumDao.filter(teacher.getSchool());
		
		// 新しいクラス番号がすでに存在し、かつそれが元のクラス番号と異なる場合はエラー
		if (list.contains(newClassNum) && !newClassNum.equals(oldClassNum)) {
			errors.put("classNum", "クラス番号が重複しています");
		}

		// エラーがある場合は変更画面に戻す
		if (!errors.isEmpty()) {
			req.setAttribute("errors", errors);
			req.setAttribute("oldClassNum", oldClassNum);
			// 入力された不正な値を残すためにセット
			req.setAttribute("classNum", newClassNum);
			req.getRequestDispatcher("class_update.jsp").forward(req, res);
			return;
		}

		// DBの更新処理
		// ※Dao側のメソッド名は設計に合わせて調整してください（例: update(School school, String oldNum, String newNum) 等）
		boolean isSuccess = classNumDao.update(teacher.getSchool(), oldClassNum, newClassNum);

		if (isSuccess) {
			// 更新成功：完了画面（class_update_done.jsp）へフォワード
			req.getRequestDispatcher("class_update_done.jsp").forward(req, res);
		} else {
			// 更新失敗（想定外のエラーなど）の処理
			errors.put("forced", "変更処理に失敗しました。時間をおいて再度お試しください。");
			req.setAttribute("errors", errors);
			req.setAttribute("oldClassNum", oldClassNum);
			req.getRequestDispatcher("class_update.jsp").forward(req, res);
		}
	}
}