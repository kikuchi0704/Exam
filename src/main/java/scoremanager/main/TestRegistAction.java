package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;


public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        // リクエストパラメータの取得
        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String subjectCode = request.getParameter("f3");

        // 入学年度リスト
        List<Integer> entYearSet = new ArrayList<>();
        int year = LocalDate.now().getYear();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }
     // 既存DAOをそのまま利用
        ClassNumDao cDao = new ClassNumDao();
        List<String> classNumSet = cDao.filter(teacher.getSchool()); // List<String>が返る
        
        //SubjectDao sDao = new SubjectDao();
       // List<Subject> subjects = sDao.filter(teacher.getSchool());
     // リクエスト属性にセット
        request.setAttribute("ent_year_set", entYearSet);
        
        request.setAttribute("class_num_set", classNumSet);
       // request.setAttribute("subjects", subjects);


       
        // 検索条件が揃っている場合（未実装のまま）
        if (entYearStr != null && !entYearStr.equals("0")
                && classNum != null && !classNum.equals("0")
                && subjectCode != null && !subjectCode.equals("0")) {
            // 本来ここで tests を取得する
        }

        // JSPへフォワード
        request.getRequestDispatcher("test_regist.jsp").forward(request, response);
    }
}
