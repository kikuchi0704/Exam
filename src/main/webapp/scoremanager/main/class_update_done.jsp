<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <div id="wrap_box" class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">クラス管理</h2>
            <div class="px-3 mt-4 text-center">
                <p class="py-3 fw-bold rounded text-dark" style="background-color:#8cc3a9; max-width: 500px; margin: 0 auto;">
                    変更が完了しました
                </p>
                <div class="mt-5">
                    <a href="ClassList.action" class="btn btn-secondary">戻る</a>
                </div>
            </div>
        </div>
    </c:param>
</c:import>