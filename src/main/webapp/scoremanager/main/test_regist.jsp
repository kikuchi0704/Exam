<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
            
            <%-- 検索フォーム --%>
            <form method="get" action="TestRegist.action">
                <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
                    <div class="col-3">
                        <label class="form-label">入学年度</label>
                        <select class="form-select" name="f1">
                            <option value="0">--------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-3">
                        <label class="form-label">クラス</label>
                        <select class="form-select" name="f2">
                            <option value="0">--------</option>
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-3">
                        <label class="form-label">科目</label>
                        <select class="form-select" name="f3">
                            <option value="0">--------</option>
                            <c:forEach var="subject" items="${subjects}">
                                <option value="${subject.cd}" <c:if test="${subject.cd==f3}">selected</c:if>>${subject.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-2">
                        <label class="form-label">回数</label>
                        <select class="form-select" name="f4">
                            <option value="1" <c:if test="${f4=='1'}">selected</c:if>>1</option>
                            <option value="2" <c:if test="${f4=='2'}">selected</c:if>>2</option>
                        </select>
                    </div>
                    <div class="col-1 text-center">
                        <button class="btn btn-secondary" id="filter-button">検索</button>
                    </div>
                </div>
            </form>

            <%-- 成績入力テーブル --%>
            <c:choose>
                <c:when test="${tests.size() > 0}">
                    <div class="px-4 mb-2">科目：${tests[0].subject.name} (${f4}回)</div>
                    <div class="mt-2 text-danger text-center">${error}</div>
                    
                    <form method="post" action="TestRegistExecute.action">
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
                                            <%-- 得点入力。バリデーションエラー時はメッセージ表示 --%>
                                            <input type="number" name="point[]" value="${test.point}" class="form-control" min="0" max="100" style="width:100px;">
                                            <input type="hidden" name="student_no[]" value="${test.student.no}">
                                            <c:if test="${!empty error}"><div class="text-warning small">0~100の範囲で入力してください</div></c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <div class="mt-3">
                            <button type="submit" class="btn btn-dark">登録して終了</button>
                        </div>
                    </form>
                </c:when>
                <c:otherwise>
                    <div class="px-4">学生情報が存在しませんでした。</div>
                </c:otherwise>
            </c:choose>
        </section>
    </c:param>
</c:import>