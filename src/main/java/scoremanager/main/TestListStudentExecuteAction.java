package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // --- 追加：再検索用にリストをセットし直す ---
        setSearchData(req, teacher);

        // 入力された学生番号を取得
        String studentNo = req.getParameter("f4");

        // 既存のStudentDaoを使用して学生情報を取得
        StudentDao sDao = new StudentDao();
        Student student = sDao.get(studentNo);

        if (student != null) {
            // 学生が存在する場合、成績リストを取得
            TestListStudentDao tDao = new TestListStudentDao();
            List<TestListStudent> tests = tDao.filter(student, teacher.getSchool());
            
            req.setAttribute("student", student);
            req.setAttribute("tests", tests);
        } else {
            // 学生が見つからない場合のエラー処理
            req.setAttribute("errors", "学生情報が存在しませんでした");
        }

        // 学生別成績一覧画面へ
        req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
    }

    /**
     * 検索ボックス用のデータを取得してリクエストにセットするメソッド
     */
    private void setSearchData(HttpServletRequest req, Teacher teacher) throws Exception {
        // 入学年度リストの作成（現在から10年前まで）
        List<Integer> entYearSet = new ArrayList<>();
        int year = java.time.LocalDate.now().getYear();
        for (int i = year; i >= year - 10; i--) {
            entYearSet.add(i);
        }
        
        // クラスリストの取得
        ClassNumDao cDao = new ClassNumDao();
        List<String> classNumSet = cDao.filter(teacher.getSchool());
        
        // 科目リストの取得
        SubjectDao sDao = new SubjectDao();
        List<Subject> subjects = sDao.filter(teacher.getSchool());

        // JSPで使う名前（ent_year_set, class_num_set, subjects）に合わせてセット
        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", classNumSet);
        req.setAttribute("subjects", subjects);
    }
}