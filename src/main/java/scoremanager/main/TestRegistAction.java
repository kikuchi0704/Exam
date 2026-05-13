package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        
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

        // JSPへ渡すデータ
        request.setAttribute("ent_year_set", entYearSet);

        // ★ エラー回避のため空リストを渡す（DAO未実装でも動く）
        List<String> classNumSet = new ArrayList<>();
        request.setAttribute("class_num_set", classNumSet);

        List<String> subjects = new ArrayList<>();
        request.setAttribute("subjects", subjects);

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
