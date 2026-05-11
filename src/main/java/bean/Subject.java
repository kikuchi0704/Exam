package bean;

<<<<<<< HEAD
public class Subject {

    private String cd;
    private String name;
    private School school;

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
=======
import java.io.Serializable;

public class Subject implements Serializable {

	/**
	 * 学校コード:String
	 */
	private String cd;

	/**
	 * 学校名:String
	 */
	private String name;

	/**
	 * 学校：school
	 */
	private School school;

	/**
	 * ゲッタ・セッタ
	 */
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
>>>>>>> branch 'master' of https://github.com/kikuchi0704/Exam.git
