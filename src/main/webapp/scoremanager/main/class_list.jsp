<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">クラス管理</h2>

            <div class="text-end mb-3">
                <a href="ClassCreate.action">新規登録</a>
            </div>

            <div class="px-3">
                <table class="table table-hover mt-3">
                    <thead>
                        <tr>
                            <th>クラス番号</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="num" items="${class_num_set}">
                            <tr>
                                <td>${num}</td>
                                <td class="text-end">
                                    <a href="ClassUpdate.action?class_num=${num}">変更</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
    </c:param>
</c:import>