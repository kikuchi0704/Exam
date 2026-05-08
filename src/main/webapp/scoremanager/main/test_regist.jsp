<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

    <c:param name="title" value="得点管理システム"/>

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