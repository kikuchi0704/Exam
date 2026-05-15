<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

            <%-- 1. 検索エリア --%>
            <div class="row border mx-3 mb-4 py-3 rounded">
                <form action="TestRegist.action" method="get">
                    <div class="row align-items-center">
                        <%-- 入学年度 --%>
                        <div class="col-3">
                            <label class="form-label">入学年度</label>
                            <select name="f1" class="form-select" required>
                                <option value="">--------</option>
                                <c:forEach var="year" items="${ent_year_set}">
                                    <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <%-- クラス --%>
                        <div class="col-3">
                            <label class="form-label">クラス</label>
                            <select name="f2" class="form-select" required>
                                <option value="">--------</option>
                                <c:forEach var="num" items="${class_num_set}">
                                    <option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <%-- 科目 --%>
                        <div class="col-3">
                            <label class="form-label">科目</label>
                            <select name="f3" class="form-select" required>
                                <option value="">--------</option>
                                <c:forEach var="sub" items="${subjects}">
                                    <option value="${sub.cd}" <c:if test="${sub.cd == f3}">selected</c:if>>${sub.name}</option>
                                </c:forEach>
                            </select>
                        </div>
<<<<<<< HEAD
                        <%-- 回数 --%>
=======
>>>>>>> branch 'master' of https://github.com/kikuchi0704/Exam.git
                        <div class="col-2">
                            <label class="form-label">回数</label>
                            <select name="f4" class="form-select" required>
                                <option value="">---</option>
                                <option value="1" <c:if test="${f4 == '1'}">selected</c:if>>1</option>
                                <option value="2" <c:if test="${f4 == '2'}">selected</c:if>>2</option>
                            </select>
                        </div>
<<<<<<< HEAD
                        <%-- 検索ボタン --%>
                        <div class="col-1 mt-4">
                            <button type="submit" class="btn btn-secondary">検索</button>
                        </div>
=======
                        <div class="col-2 text-center">
							<button class="btn btn-secondary" id="filter-button">検索</button>
						</div>
>>>>>>> branch 'master' of https://github.com/kikuchi0704/Exam.git
                    </div>
                </form>
            </div>

<<<<<<< HEAD
            <%-- 2. 検索結果・登録エリア --%>
=======
            <%-- 2. 検索結果表示エリア --%>
>>>>>>> branch 'master' of https://github.com/kikuchi0704/Exam.git
            <c:if test="${searched}">
                <div class="px-3">
                    <c:choose>
                        <%-- データあり：成績入力テーブルを表示 --%>
                        <c:when test="${not empty tests}">
                            <h4 class="mb-3 text-primary">
                                ${tests[0].subject.name} （${f4}回目）
                            </h4>

                            <%-- 全体エラー表示 --%>
                            <c:if test="${not empty error}">
                                <div class="alert alert-danger text-center">${error}</div>
                            </c:if>

                            <form action="TestRegistExecute.action" method="post">
                                <%-- 共通情報を隠しパラメータで送信 --%>
                                <input type="hidden" name="ent_year" value="${f1}">
                                <input type="hidden" name="class_num" value="${f2}">
                                <input type="hidden" name="subject_cd" value="${f3}">
                                <input type="hidden" name="count" value="${f4}">

                                <table class="table table-hover mt-3">
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
                                                    <input type="number" name="point_${test.student.no}" 
                                                           value="${test.point}" class="form-control" 
                                                           style="width: 100px;" min="0" max="100">
                                                    
                                                    <%-- どの学生の点数か識別するための隠し項目 --%>
                                                    <input type="hidden" name="student_no" value="${test.student.no}">

                                                    <%-- 個別エラー（バリデーション用） --%>
                                                    <c:if test="${not empty errors[test.student.no]}">
                                                        <div class="text-warning small">${errors[test.student.no]}</div>
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <div class="mt-4">
                                    <button type="submit" class="btn btn-dark">登録して終了</button>
                                </div>
                            </form>
                        </c:when>

                        <%-- データなし --%>
                        <c:otherwise>
                            <div class="alert alert-warning">学生情報が存在しませんでした。</div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </c:if>
        </section>
    </c:param>
</c:import>