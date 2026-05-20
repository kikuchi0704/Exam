package scoremanager.main;

import java.util.List;

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
		List<String> list = classNumDao.filter(teacher.getSchool());
		req.setAttribute("class_num_set", list);
		
		req.getRequestDispatcher("class_create.jsp").forward(req, res);
	}
}