<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>
            
            <div class="row border mx-3 mb-4 py-3 rounded">
                <form action="TestListSubjectExecute.action" method="get">
                    <div class="row align-items-center">
                        <div class="col-3">
                            <label class="form-label">入学年度</label>
                            <select name="f1" class="form-select">
							    <option value="">--------</option>
							    <c:forEach var="year" items="${ent_year_set}">
							        <%-- param.f1（検索時に送信された値）と year が一致すれば selected を出力 --%>
							        <option value="${year}" ${year == param.f1 ? 'selected' : ''}>${year}</option>
							    </c:forEach>
							</select>
                        </div>
                        <div class="col-3">
                            <label class="form-label">クラス</label>
                            <select name="f2" class="form-select">
							    <option value="">--------</option>
							    <c:forEach var="num" items="${class_num_set}">
							        <%-- param.f2 と num が一致すれば selected --%>
							        <option value="${num}" ${num == param.f2 ? 'selected' : ''}>${num}</option>
							    </c:forEach>
							</select>
                        </div>
                        <div class="col-4">
                            <label class="form-label">科目</label>
                            <select name="f3" class="form-select">
							    <option value="">--------</option>
							    <c:forEach var="sub" items="${subjects}">
							        <%-- param.f3（送信された科目コード）と sub.cd が一致すれば selected --%>
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
            
            <c:if test="${not empty student}">
                <div class="mb-3">氏名：${student.name} (${student.no})</div>
            </c:if>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>科目名</th>
                        <th>科目コード</th>
                        <th>回数</th>
                        <th>点数</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="t" items="${tests}">
                        <tr>
                            <td>${t.subjectName}</td>
                            <td>${t.subjectCd}</td>
                            <td>${t.num}回</td>
                            <td>${t.point}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="mt-3"><a href="TestList.action">戻る</a></div>
        </section>
    </c:param>
</c:import>