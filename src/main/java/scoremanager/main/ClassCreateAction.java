package scoremanager.main;

import java.time.LocalDate;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassCreateAction extends Action {
	
	@Override
	public void execute (HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		HttpSession sesssion = req.getSession();
		Teacher teacher = (Teacher)sesssion.getAttribute("user");
		
		ClassNumDao classNumDao = new ClassNumDao();
		LocalDate todaysDate = LocalDate.now();
	}
}
