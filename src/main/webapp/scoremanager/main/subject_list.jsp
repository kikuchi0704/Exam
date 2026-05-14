<%-- 科目一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

    <c:param name="title">
        得点管理システム - 科目一覧
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">

        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                科目一覧
            </h2>

            <div class="my-2 text-end px-4">
                <a href="subject_create.jsp" class="btn btn-success btn-sm">
                    新規登録
                </a>
            </div>

            <c:choose>

                <c:when test="${subjects != null && subjects.size() > 0}">

                    <div class="px-4 mb-2">
                        検索結果：${subjects.size()}件
                    </div>

                    <table class="table table-hover">

                        <thead>
                            <tr>
                                <th>科目コード</th>
                                <th>科目名</th>
                                <th class="text-end">操作</th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="subject" items="${subjects}">
    <tr>

        <td>${subject.cd}</td>

        <td>${subject.name}</td>

        <!-- 操作ボタン -->
        <td class="text-end">

            <a href="SubjectUpdate.action?cd=${subject.cd}">
                変更
            </a>

            <a href="SubjectDelete.action?cd=${subject.cd}"
               onclick="return confirm('削除しますか？');">
                削除
            </a>

        </td>

    </tr>
</c:forEach>
                        </tbody>

                    </table>

                </c:when>

                <c:otherwise>

                    <div class="px-4">
                        科目情報が存在しませんでした。
                    </div>

                </c:otherwise>

            </c:choose>

        </section>

    </c:param>

</c:import>