package bean;

import java.io.Serializable;
<<<<<<< HEAD
=======

public class Subject implements Serializable {
>>>>>>> branch 'master' of https://github.com/kikuchi0704/Exam.git

public class Subject implements Serializable {

    /**
     * 科目コード
     */
    private String cd;

    /**
     * 科目名
     */
    private String name;

    /**
     * 学校
     */
    private School school;

    // ゲッタ・セッタ
    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}