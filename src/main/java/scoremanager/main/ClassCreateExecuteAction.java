package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassCreateExecuteAction extends Action {
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		HttpSession session = req.getSession(); // セッション
		Teacher teacher = (Teacher)session.getAttribute("user");
		
		// 1. 画面からの入力値（パラメータ）を受け取る
		String class_num = req.getParameter("class_num"); 
		
		ClassNum classNum = new ClassNum();
		ClassNumDao classNumDao = new ClassNumDao();
		Map<String, String> errors = new HashMap<>();
		
		// nullチェック（念のための安全対策）
		if (class_num == null) {
			class_num = "";
		}
		
		// 2. ビジネスロジック
		if (class_num.length() != 3 ) { // クラス番号が３文字ではなかった場合
			errors.put("1", "クラス番号は3文字で入力してください");
			req.setAttribute("errors", errors);
		} else {
			if (classNumDao.get(class_num, teacher.getSchool()) != null) { // クラス番号が重複している場合
				errors.put("2", "クラス番号が重複しています");
				req.setAttribute("errors", errors);
			} else {
				// クラス情報をセット
				classNum.setSchool(teacher.getSchool());
				classNum.setClass_num(class_num);
				// saveメソッドで情報を登録
				classNumDao.save(classNum);
			}
		}

		// 3. 入力画面に戻ったとき、入力していた文字が消えないようにリクエストにセット
		req.setAttribute("class_num", class_num);
		
		// 4. JSPへのフォワード制御
		if (errors.isEmpty()) {
			// 登録完了画面にフォワード
			req.getRequestDispatcher("class_create_done.jsp").forward(req, res);
		} else { // エラーメッセージがある場合
			// 入力画面（Action）にフォワード
			req.getRequestDispatcher("ClassCreate.action").forward(req, res);
		}
	}
}