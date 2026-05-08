package scoremanager.main;
 
 
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;
// DAOをインポート
 
public class TestRegistExecuteAction extends Action {
 
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // JSPの各行の得点入力を取得 (配列として受け取る)
        String[] studentNos = request.getParameterValues("student_no[]");
        String[] points = request.getParameterValues("point[]");
        
        // バリデーションチェック (0~100の範囲など)
        boolean hasError = false;
        for (String p : points) {
            int point = Integer.parseInt(p);
            if (point < 0 || point > 100) {
                hasError = true;
                break;
            }
        }
 
        if (hasError) {
            request.setAttribute("error", "0〜100の範囲で入力してください");
            // 前の画面に戻る処理
        } else {
            // DAOを使用してDB保存 (List<Test>を作成して一括保存など)
            // testDao.save(testList);
            
            // 完了画面へリダイレクト
            response.sendRedirect("test_regist_done.jsp");
        }
    }
}