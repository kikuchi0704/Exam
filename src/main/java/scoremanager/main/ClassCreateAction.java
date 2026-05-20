package scoremanager.main;

import java.util.List;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassCreateAction extends Action {
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		HttpSession session = req.getSession(); 
		Teacher teacher = (Teacher) session.getAttribute("user");
		
		ClassNumDao classNumDao = new ClassNumDao();
		
		String classStr = req.getParameter("class_num");
		
		ClassNum classNum = new ClassNum();
		classNum.setSchool(teacher.getSchool());
		classNum.setClass_num(classStr);
		
		// クラス情報をDBに登録
		classNumDao.save(classNum);
		
		// 最新のクラス一覧を取得
		List<String> list = classNumDao.filter(teacher.getSchool());
		
		// JSPへ引き渡す
		req.setAttribute("class_num_set", list);
		
		req.getRequestDispatcher("class_create.jsp").forward(req, res);
	}
}