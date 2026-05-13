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
            <form method="get" action="TestRegist.action">

                <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">

                    <%-- 入学年度 --%>
                    <div class="col-3">
                        <label class="form-label">入学年度</label>

                        <select class="form-select" name="f1">
                            <option value="">--------</option>

                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}"
                                    <c:if test="${year == f1}">
                                        selected
                                    </c:if>>
                                    ${year}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- クラス --%>
                    <div class="col-3">
                        <label class="form-label">クラス</label>

                        <select class="form-select" name="f2">
                            <option value="">--------</option>

                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}"
                                    <c:if test="${num == f2}">
                                        selected
                                    </c:if>>
                                    ${num}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- 科目 --%>
                    <div class="col-3">
                        <label class="form-label">科目</label>

                        <select class="form-select" name="f3">
                            <option value="">--------</option>

                            <c:forEach var="subject" items="${subjects}">
                                <option value="${subject.cd}"
                                    <c:if test="${subject.cd == f3}">
                                        selected
                                    </c:if>>
                                    ${subject.name}
                                </option>
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

                    <%-- 検索ボタン --%>
                    <div class="col-1 text-center">
                        <button type="submit" class="btn btn-secondary" id="filter-button">
                            検索
                        </button>
                    </div>

                </div>

            </form>

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