package scoremanager.main;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> branch 'master' of https://github.com/kikuchi0704/Exam.git
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッション取得 1
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

<<<<<<< HEAD
        // ローカル変数の指定 2
        SubjectDao subjectDao = new SubjectDao();
=======
		// ローカル変数の指定 1
		SubjectDao subjectDao = new SubjectDao(); // 科目Daoを初期化
>>>>>>> branch 'master' of https://github.com/kikuchi0704/Exam.git

        // リクエストパラメータの取得 3
        String subjectCd = req.getParameter("subject_cd");
        String subjectName = req.getParameter("subject_name");

<<<<<<< HEAD
        // ビジネスロジック 4
        Subject subject = new Subject();
        subject.setCd(subjectCd);
        subject.setName(subjectName);
        subject.setSchool(teacher.getSchool());
=======
		// DBからデータ取得 3
		// ログインユーザーの学校コードをもとに科目の一覧を取得
		List<Subject> list = subjectDao.filter(teacher.getSchool());
>>>>>>> branch 'master' of https://github.com/kikuchi0704/Exam.git

<<<<<<< HEAD
        // DBへ登録 5
        subjectDao.save(subject);
=======
		// ビジネスロジック 4
		// リストを初期化
>>>>>>> branch 'master' of https://github.com/kikuchi0704/Exam.git

<<<<<<< HEAD
        // レスポンス値をセット 6
        // （今回は完了画面なので特になし）
=======
		// レスポンス値をセット 6
		// リクエストにデータをセット
		req.setAttribute("subject_set", list);
>>>>>>> branch 'master' of https://github.com/kikuchi0704/Exam.git

<<<<<<< HEAD
        // JSPへフォワード 7
        req.getRequestDispatcher("subject_create_done.jsp")
           .forward(req, res);
    }
}
=======
		// JSPへフォワード 7
		req.getRequestDispatcher("subject_create.jsp").forward(req, res);
	}

}
>>>>>>> branch 'master' of https://github.com/kikuchi0704/Exam.git
