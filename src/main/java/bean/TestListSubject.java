package bean;

import java.io.Serializable;
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
	 * 
	 */
	private Map<Integer, Integer> points;
	
	/**
	 * ゲッタ・セッタ
	 */
	public int getEntYear() {
		return entYear;
	}

	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}
	
	public String getstudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentno) {
		this.studentNo = studentno;
		
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

	public String getStudentNo() {
		return studentNo;
	}


}
