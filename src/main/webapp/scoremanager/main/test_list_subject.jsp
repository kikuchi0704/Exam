<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <%-- (上部 import や c:import はそのまま) --%>

		<section class="me-4">
		    <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>
		
		    <%-- ★★★ 検索ボックスエリア：常に両方表示する ★★★ --%>
		    <div class="row border mx-3 mb-4 py-3 rounded">
		        <form action="TestListSubjectExecute.action" method="get" class="mb-3">
		            <div class="row align-items-center">
		                <p class="text-secondary small mb-1">科目情報</p>
		                <div class="col-3">
		                    <label class="form-label">入学年度</label>
		                    <select name="f1" class="form-select" required>
		                        <option value="">--------</option>
		                        <c:forEach var="year" items="${ent_year_set}">
		                            <option value="${year}" ${year == param.f1 ? 'selected' : ''}>${year}</option>
		                        </c:forEach>
		                    </select>
		                </div>
		                <div class="col-3">
		                    <label class="form-label">クラス</label>
		                    <select name="f2" class="form-select" required>
		                        <option value="">--------</option>
		                        <c:forEach var="num" items="${class_num_set}">
		                            <option value="${num}" ${num == param.f2 ? 'selected' : ''}>${num}</option>
		                        </c:forEach>
		                    </select>
		                </div>
		                <div class="col-4">
		                    <label class="form-label">科目</label>
		                    <select name="f3" class="form-select" required>
		                        <option value="">--------</option>
		                        <c:forEach var="sub" items="${subjects}">
		                            <option value="${sub.cd}" ${sub.cd == param.f3 ? 'selected' : ''}>${sub.name}</option>
		                        </c:forEach>
		                    </select>
		                </div>
		                <div class="col-2">
		                    <button class="btn btn-secondary w-100">検索</button>
		                </div>
		            </div>
		        </form>
		
		        <hr class="my-3">
		
		        <form action="TestListStudentExecute.action" method="get">
		            <div class="row align-items-center">
		                <p class="text-secondary small mb-1">学生情報</p>
		                <div class="col-10">
		                    <label class="form-label">学生番号</label>
		                    <input type="text" name="f4" value="${param.f4}" class="form-control" placeholder="学生番号を入力してください" required>
		                </div>
		                <div class="col-2">
		                    <button class="btn btn-secondary w-100">検索</button>
		                </div>
		            </div>
		        </form>
		    </div>
		
		    <%-- ★★★ 結果表示エリア ★★★ --%>
		    <c:choose>
		        <%-- ケース1：科目検索の結果（リストがある場合） --%>
		        <c:when test="${not empty tests}">
		            <table class="table table-hover">
		                <thead>
		                    <tr>
		                        <th>入学年度</th><th>クラス</th><th>学生番号</th><th>氏名</th><th>1回</th><th>2回</th>
		                    </tr>
		                </thead>
		                <tbody>
		                    <c:forEach var="test" items="${tests}">
		                        <tr>
		                            <td>${test.entYear}</td>
		                            <td>${test.classNum}</td>
		                            <td>${test.studentNo}</td>
		                            <td>${test.studentName}</td>
		                            <td>${test.getPoint(1)}</td>
		                            <td>${test.getPoint(2)}</td>
		                        </tr>
		                    </c:forEach>
		                </tbody>
		            </table>
		        </c:when>
		
		        <%-- ケース2：学生検索の結果（個人の成績リストがある場合） --%>
		        <c:when test="${not empty studentTests}">
		            <div class="mb-3">氏名：${student.name} (${student.no})</div>
		            <table class="table table-hover">
		                <thead>
		                    <tr>
		                        <th>科目名</th><th>科目コード</th><th>回数</th><th>点数</th>
		                    </tr>
		                </thead>
		                <tbody>
		                    <c:forEach var="t" items="${studentTests}">
		                        <tr>
		                            <td>${t.subjectName}</td>
		                            <td>${t.subjectCd}</td>
		                            <td>${t.num}回</td>
		                            <td>${t.point}</td>
		                        </tr>
		                    </c:forEach>
		                </tbody>
		            </table>
		        </c:when>
		
		        <%-- 何も検索されていない、または結果が0件の場合 --%>
		        <c:otherwise>
		            <c:if test="${not empty param.f1 or not empty param.f4}">
		                <div class="text-warning">成績情報が存在しませんでした</div>
		            </c:if>
		            <c:if test="${empty param.f1 and empty param.f4}">
		                 <div class="text-info">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</div>
		            </c:if>
		        </c:otherwise>
		    </c:choose>
		
		</section>
    </c:param>
</c:import>