<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">

        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                成績管理
            </h2>

            <%-- 検索フォーム --%>
               <%-- 1. 科目情報から検索 --%>
            <div class="row border mx-3 mb-4 py-3 rounded">
                <form action="TestRegistExecute.action" method="get">
                    <div class="row align-items-center">
                        <div class="col-3">
                            <label class="form-label">入学年度</label>
                            <select name="f1" class="form-select" required>
                                <option value="">--------</option>
                                <c:forEach var="year" items="${ent_year_set}">
                                    <option value="${year}">${year}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-3">
                            <label class="form-label">クラス</label>
                            <select name="f2" class="form-select" required>
                                <option value="">--------</option>
                                <c:forEach var="num" items="${class_num_set}">
                                    <option value="${num}">${num}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-4">
                            <label class="form-label">科目</label>
                            <select name="f3" class="form-select" required>
                                <option value="">--------</option>
                                <c:forEach var="sub" items="${subjects}">
                                    <option value="${sub.cd}">${sub.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <%-- 回数 --%>
	                    <div class="col-2">
	                        <label class="form-label">回数</label>
	
	                        <select class="form-select" name="f4">
	                            <option value="1"
	                                <c:if test="${f4 == '1'}">
	                                    selected
	                                </c:if>>
	                                1
	                            </option>
	
	                            <option value="2"
	                                <c:if test="${f4 == '2'}">
	                                    selected
	                                </c:if>>
	                                2
	                            </option>
	                        </select>
	                    </div>
	                    <div class="col-2">
                            <button class="btn btn-secondary w-100">検索</button>
                        </div>
                    </div>
                </form>
            </div>

            <%-- 成績一覧表示 --%>
            <c:choose>

                <%-- データあり --%>
                <c:when test="${not empty tests}">

                    <div class="px-4 mb-2">
                        科目：${tests[0].subject.name}（${f4}回）
                    </div>

                    <%-- 全体エラー --%>
                    <c:if test="${not empty error}">
                        <div class="mt-2 text-danger text-center">
                            ${error}
                        </div>
                    </c:if>

                    <%-- 登録フォーム --%>
                    <form method="post" action="TestRegistExecute.action">

                        <%-- hidden項目 --%>
                        <input type="hidden" name="subject_cd" value="${f3}">
                        <input type="hidden" name="count" value="${f4}">
                        <input type="hidden" name="ent_year" value="${f1}">
                        <input type="hidden" name="class_num" value="${f2}">

                        <table class="table table-hover">

                            <thead>
                                <tr>
                                    <th>入学年度</th>
                                    <th>クラス</th>
                                    <th>学生番号</th>
                                    <th>氏名</th>
                                    <th>点数</th>
                                </tr>
                            </thead>

                            <tbody>

                                <c:forEach var="test" items="${tests}">

                                    <tr>

                                        <td>${test.student.entYear}</td>

                                        <td>${test.student.classNum}</td>

                                        <td>${test.student.no}</td>

                                        <td>${test.student.name}</td>

                                        <td>

                                            <%-- 点数入力 --%>
                                            <input
                                                type="number"
                                                name="point_${test.student.no}"
                                                value="${test.point}"
                                                class="form-control"
                                                min="0"
                                                max="100"
                                                style="width:100px;">

                                            <%-- 学生番号保持 --%>
                                            <input
                                                type="hidden"
                                                name="student_no"
                                                value="${test.student.no}">

                                            <%-- 個別エラー表示 --%>
                                            <c:if test="${errors[test.student.no] != null}">
                                                <div class="text-warning small">
                                                    ${errors[test.student.no]}
                                                </div>
                                            </c:if>

                                        </td>

                                    </tr>

                                </c:forEach>

                            </tbody>

                        </table>

                        <%-- 登録ボタン --%>
                        <div class="mt-3">
                            <button type="submit" class="btn btn-dark">
                                登録して終了
                            </button>
                        </div>

                    </form>

                </c:when>

                <%-- データなし --%>
                <c:otherwise>

                    <%-- 検索後のみ表示 --%>
                    <c:if test="${searched}">
                        <div class="px-4">
                            学生情報が存在しませんでした。
                        </div>
                    </c:if>

                </c:otherwise>

            </c:choose>

        </section>

    </c:param>

</c:import>