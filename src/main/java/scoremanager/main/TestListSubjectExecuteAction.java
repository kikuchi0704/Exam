package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 【重要】再検索用にリストをセットし直す
        setSearchData(req, teacher); 

        // 入力値取得
        String entYearStr = req.getParameter("f1");
        String classNum = req.getParameter("f2");
        String subjectCd = req.getParameter("f3");

        if (entYearStr != null && classNum != null && subjectCd != null) {
            int entYear = Integer.parseInt(entYearStr);
            SubjectDao sDao = new SubjectDao();
            Subject subject = sDao.get(subjectCd, teacher.getSchool());
            TestListSubjectDao dao = new TestListSubjectDao();
            List<TestListSubject> list = dao.filter(entYear, classNum, subject, teacher.getSchool());
            req.setAttribute("tests", list);
        }
        
        req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
    }

    // 共通して使うリスト作成メソッド（Action内に追加するか共通クラスにする）
    private void setSearchData(HttpServletRequest req, Teacher teacher) throws Exception {
        List<Integer> entYearSet = new ArrayList<>();
        int year = java.time.LocalDate.now().getYear();
        for (int i = year; i >= year - 10; i--) entYearSet.add(i);
        
        ClassNumDao cDao = new ClassNumDao();
        List<String> classNumSet = cDao.filter(teacher.getSchool());
        
        SubjectDao sDao = new SubjectDao();
        List<Subject> subjects = sDao.filter(teacher.getSchool());

        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", classNumSet);
        req.setAttribute("subjects", subjects);
    }
}