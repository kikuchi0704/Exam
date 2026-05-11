<%-- 科目情報変更JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

    <c:param name="title">
        得点管理システム - 科目変更
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">

        <section>

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                科目変更
            </h2>

            <form action="SubjectUpdateExecute.action" method="post">

                <div>
                    <label for="cd">科目コード</label><br>
                    <input type="text"
                           id="cd"
                           name="cd"
                           value="${cd}"
                           readonly />
                </div>

                <div>
                    <label for="name">科目名</label><br>
                    <input type="text"
                           id="name"
                           name="name"
                           value="${name}"
                           required />
                </div>

                <div class="mt-3">
                    <input class="btn btn-primary" type="submit" value="変更"/>
                </div>

            </form>

            <a href="SubjectList.action">戻る</a>

        </section>

    </c:param>

</c:import>