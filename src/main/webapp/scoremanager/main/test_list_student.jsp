<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">
    	得点管理システム
    </c:param>
    	
    <c:param name="scripts"></c:param>
    	
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績一覧（学生）</h2>
            
            <%-- 検索フィルター外枠 --%>
            <div class="border mx-3 mb-3 p-4 rounded">
                <%-- 科目別検索フォーム --%>
                <form action="TestListSubjectExecute.action" method="get" class="row align-items-end mb-4 pb-4 border-bottom">
                    <div class="col-2 text-center">科目情報</div>
                    <div class="col-2">
                        <label class="form-label">入学年度</label>
                        <select class="form-select" name="f1">
                            <option value="0">--------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-2">
                        <label class="form-label">クラス</label>
                        <select class="form-select" name="f2">
                            <option value="0">--------</option>
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-4">
                        <label class="form-label">科目</label>
                        <select class="form-select" name="f3">
                            <option value="0">--------</option>
                            <c:forEach var="sub" items="${subjects}">
                                <option value="${sub.cd}" <c:if test="${sub.cd==f3}">selected</c:if>>${sub.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-2 text-center">
                        <button class="btn btn-secondary" id="filter-button">検索</button>
                    </div>
                </form>

                <%-- 学生別検索フォーム --%>
                <form action="TestListStudentExecute.action" method="get" class="row align-items-end">
                    <div class="col-2 text-center">学生情報</div>
                    <div class="col-4">
                        <label class="form-label">学生番号</label>
                        <input type="text" name="f4" class="form-control" placeholder="学生番号を入力してください" required>
                    </div>
                    <div class="col-2 text-center">
                        <button class="btn btn-secondary" id="filter-button">検索</button>
                    </div>
                </form>
            </div>

            <%-- 検索結果表示エリア --%>
            <div class="mx-3">
                <%-- 氏名表示 --%>
                <c:if test="${not empty student}">
                    <div class="mb-2">氏名：${student.name} (${student.no})</div>
                </c:if>

                <c:choose>
                    <%-- 成績データがある場合 --%>
                    <c:when test="${tests.size() > 0}">
                        <table class="table table-hover mt-3">
                            <thead>
                                <tr>
                                    <th>科目名</th>
                                    <th>科目コード</th>
                                    <th>回数</th>
                                    <th>点数</th>
                                </tr>
                            </thead>
                            <tbody>
								<c:forEach var="test" items="${tests}">
								    <tr>
								        <td>${test.subjectName}</td>
								        <td>${test.subjectCd}</td>
								        <td>${test.num}回</td> 
								        <td>${test.point}</td>
								    </tr>
								</c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <%-- 成績データがない場合 --%>
                    <c:otherwise>
                        <div class="mt-2">成績情報が存在しませんでした</div>
                    </c:otherwise>
                </c:choose>
            </div>
        </section>
    </c:param>
</c:import>