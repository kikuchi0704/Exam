<%-- subject_update.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム - 科目変更</c:param>
    <c:param name="content">
        <section>
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>

            <%-- エラーメッセージ表示（画像3の①に対応） --%>
            <c:if test="${not empty errors}">
                <div class="text-danger mb-3">${errors}</div>
            </c:if>

            <form action="SubjectUpdateExecute.action" method="post">
<<<<<<< HEAD

                <div>
                    <label for="cd">科目コード</label><br>
                    <input type="text"
                           id="cd"
                           name="cd"
                           value="${cd}"
                            />
=======
                <div class="mb-3">
                    <label class="form-label">科目コード</label><br>
                    <%-- 画像1の③：科目コードは編集不可なのでテキストで表示 --%>
                    <span>${subject_cd}</span>
                    <%-- サーバーに送信するために hidden で保持 --%>
                    <input type="hidden" name="subject_cd" value="${subject_cd}">
>>>>>>> branch 'master' of https://github.com/kikuchi0704/Exam.git
                </div>

                <div class="mb-3">
                    <label class="form-label" for="subject_name">科目名</label><br>
                    <%-- 画像1の⑤：科目名の入力フィールド --%>
                    <input class="form-control" type="text" id="subject_name" 
                           name="subject_name" value="${subject_name}" 
                           placeholder="科目名を入力してください" required />
                </div>

                <div class="mt-3">
                    <input class="btn btn-primary" type="submit" value="変更"/>
                </div>
            </form>

            <div class="mt-3">
                <a href="SubjectList.action">戻る</a>
            </div>
        </section>
    </c:param>
</c:import>