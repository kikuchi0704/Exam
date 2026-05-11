package scoremanager.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 入学年度リストの作成（現在から10年前まで）
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        // クラス一覧、科目一覧を取得
        ClassNumDao cDao = new ClassNumDao();
        List<String> classNumSet = cDao.filter(teacher.getSchool());
        
        SubjectDao sDao = new SubjectDao();
        // bean.Subject のリストとして取得される
        List<Subject> subjects = sDao.filter(teacher.getSchool());

        // リクエスト属性にセット
        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("class_num_set", classNumSet);
        request.setAttribute("subjects", subjects);

        // 成績参照画面へフォワード
        request.getRequestDispatcher("test_list.jsp").forward(request, response);
    }
}