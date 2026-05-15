<%-- 科目登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section>
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                科目登録
            </h2>
            
            <form action="SubjectCreateExecute.action" method="post">
                
                <%-- 科目コード入力エリア --%>
                <div>
                    <label for="subject_cd">科目コード</label><br>
                    <input class="form-control"
                           type="text"
                           id="subject_cd"
                           name="subject_cd"
                           value="${subject_cd}"
                           required
                           <%-- 3文字のみというルールに合わせるなら maxlength も 3 にすると親切です --%>
                           maxlength="3" 
                           placeholder="科目コード（3文字）を入力してください" />
                </div>
                
                <%-- エラー表示エリア（科目コード用） --%>
                <%-- Java側からエラーを "subject_cd_error" という名前で送る想定 --%>
                <c:if test="${not empty errors.get('subject_cd_error')}">
                    <div class="mt-2 text-danger">
                        ${errors.get("subject_cd_error")}
                    </div>
                </c:if>

                <div class="mt-4">
                    <label for="subject_name">科目名</label><br>
                    <input class="form-control"
                           type="text"
                           id="subject_name"
                           name="subject_name"
                           value="${subject_name}"
                           required
                           maxlength="30"
                           placeholder="科目名を入力してください" />
                </div>
                
                <%-- エラー表示エリア（科目名用） --%>
                <c:if test="${not empty errors.get('subject_name_error')}">
                    <div class="mt-2 text-danger">
                        ${errors.get("subject_name_error")}
                    </div>
                </c:if>

                <div class="mx-auto py-3">
                    <button class="btn btn-secondary" type="submit">
                        登録
                    </button>
                </div>
            </form>
            
            <a href="SubjectList.action">戻る</a>
        </section>
    </c:param>
</c:import>