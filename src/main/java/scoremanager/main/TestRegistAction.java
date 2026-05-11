package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;
// 各種DAOをインポート (StudentDao, SubjectDao, ClassNumDao, TestDao等)

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // リクエストパラメータの取得
        String entYearStr = request.getParameter("f1"); // 入学年度
        String classNum = request.getParameter("f2");   // クラス
        String subjectCode = request.getParameter("f3"); // 科目コード
       // String numStr = request.getParameter("f4");     // 回数

        // 絞り込み用リストの準備 (本来はDAOから取得)
        // 例: classNumDao.filter(teacher.getSchool());
        List<Integer> entYearSet = new ArrayList<>();
        int year = LocalDate.now().getYear();
        for (int i = year - 10; i <= year; i++) entYearSet.add(i);
        
        // JSPへ渡すデータ
        request.setAttribute("ent_year_set", entYearSet);
        // request.setAttribute("class_num_set", classNumSet);
        // request.setAttribute("subjects", subjects);

        if (entYearStr != null && !entYearStr.equals("0") && classNum != null && !classNum.equals("0") && subjectCode != null && !subjectCode.equals("0")) {
            // 検索処理: 指定条件に合致する学生と現在の得点を取得
            // List<Test> tests = testDao.filter(entYear, classNum, subject, num, school);
            // request.setAttribute("tests", tests);
        }

        // JSPへフォワード
        request.getRequestDispatcher("test_regist.jsp").forward(request, response);
    }
}