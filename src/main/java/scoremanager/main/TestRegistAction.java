package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // --- 1. 入力値の取得 ---
        String entYearStr = request.getParameter("f1");     // 入学年度
        String classNum = request.getParameter("f2");      // クラス番号
        String subjectCode = request.getParameter("f3");   // 科目コード
        String numStr = request.getParameter("f4");        // 回数

        // --- 2. 選択肢（ドロップダウン）の準備 ---
        List<Integer> entYearSet = new ArrayList<>();
        int year = LocalDate.now().getYear();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }
        ClassNumDao cDao = new ClassNumDao();
        List<String> classNumSet = cDao.filter(teacher.getSchool());
        SubjectDao sDao = new SubjectDao();
        List<Subject> subjects = sDao.filter(teacher.getSchool());

        // --- 3. 検索処理 ---
        // すべての条件が入力されている場合のみ実行
        if (entYearStr != null && !entYearStr.equals("") && classNum != null && !classNum.equals("") &&
            subjectCode != null && !subjectCode.equals("") && numStr != null && !numStr.equals("")) {

            int entYear = Integer.parseInt(entYearStr);
            int num = Integer.parseInt(numStr);

            // 科目名を表示するために、コードから科目情報を取得
            Subject selectedSubject = sDao.get(subjectCode, teacher.getSchool());

            // ユーザーが作成した filter メソッドを呼び出し
            TestDao tDao = new TestDao();
            List<Test> tests = tDao.filter(entYear, classNum, selectedSubject, num, teacher.getSchool());
            
            // JSPに渡すデータをセット
            request.setAttribute("tests", tests);
            request.setAttribute("selected_subject", selectedSubject); // 科目名表示用
            request.setAttribute("searched", true); 
        }

        // 共通データのセット
        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("class_num_set", classNumSet);
        request.setAttribute("subjects", subjects);
        
        // 選択状態の維持
        request.setAttribute("f1", entYearStr);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", subjectCode);
        request.setAttribute("f4", numStr);

        request.getRequestDispatcher("test_regist.jsp").forward(request, response);
    }
}