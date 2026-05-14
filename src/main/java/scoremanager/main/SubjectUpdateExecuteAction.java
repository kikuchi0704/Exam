package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

	// SubjectUpdateExecuteAction.java 内の execute メソッド
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	    HttpSession session = req.getSession();
	    Teacher teacher = (Teacher) session.getAttribute("user");

	    // 1. JSPの <input name="..."> と名前を合わせる
	    String cd = req.getParameter("cd"); // JSPが name="cd" の場合
	    String name = req.getParameter("name"); // JSPが name="name" の場合

	    // 2. Beanにセット
	    Subject subject = new Subject();
	    subject.setCd(cd);
	    subject.setName(name);
	    subject.setSchool(teacher.getSchool());

	    SubjectDao dao = new SubjectDao();

	    // --- 修正箇所：ここにあった SubjectList.action への forward を削除 ---

	    // 3. DAOのupdateメソッドを呼び出す（save ではなく update）
	    boolean isSuccess = dao.update(subject);

	    if (isSuccess) {
	        // 更新成功：完了画面へ
	        req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
	    } else {
	        // 更新失敗：エラーメッセージをセットして元の画面へ
	        req.setAttribute("errors", "科目が存在しません");
	        req.setAttribute("cd", cd);
	        req.setAttribute("name", name);
	        req.getRequestDispatcher("subject_update.jsp").forward(req, res);
	    }
	}}