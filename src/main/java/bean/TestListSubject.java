package bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TestListSubject implements Serializable {

    /**
     * 入学年度：int
     */
    private int entYear;

    /**
     * 学生番号：String
     */
    private String studentNo;

    /**
     * 学生名：String
     */
    private String studentName;

    /**
     * クラス番号：String
     */
    private String classNum;

    /**
     * 回数と点数のマップ
     */
    private Map<Integer, Integer> points = new HashMap<>();

    public TestListSubject() {
    }
    
    /**
     * 点数をマップに追加する
     * @param key 回数(1回, 2回...)
     * @param value 点数
     */
    public void putPoint(int key, int value) {
        // pointsが初期化されていないと、ここでNullPointerExceptionが発生します
        this.points.put(key, value);
    }
    /**
     * JSPから確実に点数を取得するための専用メソッド
     * @param key 回数 (1 または 2)
     * @return 点数があればその点数、なければ "-"（ハイフン）を返す
     */
    public String getPoint(int key) {
        // Mapの中に指定された回数(1や2)のデータがあるかチェック
        if (this.points.containsKey(key)) {
            // あれば、その点数を文字列にして返す
            return String.valueOf(this.points.get(key));
        } else {
            // なければ "-" を返す
            return "-";
        }
    }
    
    /**
     * ゲッタ・セッタ
     */
    public int getEntYear() {
        return entYear;
    }

    public void setEntYear(int entYear) {
        this.entYear = entYear;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public Map<Integer, Integer> getPoints() {
        return points;
    }

    public void setPoints(Map<Integer, Integer> points) {
        this.points = points;
    }
}