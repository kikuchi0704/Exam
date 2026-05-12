<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>
            
            <%-- 1. 科目情報から検索 --%>
            <div class="row border mx-3 mb-4 py-3 rounded">
                <form action="TestListSubjectExecute.action" method="get">
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
                        <div class="col-2">
                            <button class="btn btn-secondary w-100">検索</button>
                        </div>
                    </div>
                </form>
            </div>

            <%-- 2. 学生情報から検索 --%>
            <div class="row border mx-3 py-3 rounded">
                <form action="TestListStudentExecute.action" method="get">
                    <div class="row align-items-center">
                        <div class="col-10">
                            <label class="form-label">学生番号</label>
                            <input type="text" name="f4" class="form-control" placeholder="学生番号を入力してください" required>
                        </div>
                        <div class="col-2">
                            <button class="btn btn-secondary w-100">検索</button>
                        </div>
                    </div>
                </form>
            </div>
        </section>
    </c:param>
</c:import>