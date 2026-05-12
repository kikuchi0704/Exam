<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <%-- 成績参照タイトル --%>
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>
			
			<%-- 1. 科目別検索フォーム --%>
			<div class="row border mx-3 mb-4 py-3 rounded">
			    <form action="TestListSubjectExecute.action" method="get">
			        <div class="row align-items-center">
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
			                <button class="btn btn-secondary w-100">表示</button>
			            </div>
			        </div>
			    </form>
			</div>
			
			<%-- 2. 学生番号検索フォーム (科目別の画面でも検索できるように追加) --%>
			<div class="row border mx-3 mb-4 py-3 rounded">
			    <form action="TestListStudentExecute.action" method="get">
			        <div class="row align-items-center">
			            <div class="col-10">
			                <label class="form-label">学生番号</label>
			                <input type="text" name="f4" class="form-control" placeholder="学生番号を入力してください" value="${param.f4}" required>
			            </div>
			            <div class="col-2">
			                <button class="btn btn-secondary w-100">表示</button>
			            </div>
			        </div>
			    </form>
			</div>
			
            <%-- テーブル表示部分 --%>
            <c:choose>
                <c:when test="${not empty tests}">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>入学年度</th>
                                <th>クラス</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>1回</th>
                                <th>2回</th>
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
                <c:otherwise>
                    <div class="text-warning">成績情報が存在しませんでした</div>
                </c:otherwise>
            </c:choose>
            <div class="mt-3"><a href="TestList.action" class="btn btn-outline-secondary">戻る</a></div>
        </section>
    </c:param>
</c:import>