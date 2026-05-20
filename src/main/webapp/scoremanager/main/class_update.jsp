<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">クラス情報変更</h2>

            <div class="px-3" style="max-width: 500px;">
                <%-- エラーメッセージがある場合に表示 --%>
                <c:if test="${not empty errors}">
                    <div class="alert alert-danger py-2">
                        <c:forEach var="error" items="${errors}">
                            <div>${error.value}</div>
                        </c:forEach>
                    </div>
                </c:if>

                <form action="ClassUpdateExecute.action" method="post" class="mt-4">
                    <%-- 元のクラス番号を特定して更新条件にするため、隠しパラメータで送信 --%>
                    <input type="hidden" name="oldClassNum" value="${oldClassNum}">

                    <div class="mb-4">
                        <label class="form-label fw-bold">クラス番号</label>
                        <%-- 入力エラーで戻ってきた場合は入力値を残し、初期表示時は元の値を表示 --%>
                        <input type="text" name="classNum" 
                               value="${not empty classNum ? classNum : oldClassNum}" 
                               class="form-control" style="width: 200px;" required
                               oninvalid="this.setCustomValidity('クラス番号を入力してください')"
                               oninput="this.setCustomValidity('')">
                    </div>

                    <div class="mt-4">
                        <button type="submit" class="btn btn-dark me-2">変更</button>
                        <a href="ClassList.action" class="btn btn-outline-secondary">戻る</a>
                    </div>
                </form>
            </div>
        </section>
    </c:param>
</c:import>