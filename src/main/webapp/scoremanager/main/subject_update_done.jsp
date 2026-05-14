<<<<<<< HEAD
<%-- 学生変更完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="content">
		<div id="wrap_box">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2">科目情報変更</h2>
			<div id="wrap_box">
				<p class="text-center" style="background-color:#8cc3a9">変更が完了しました</p>
				<br>
				<br>
				<br>
				<a href="StudentList.action">科目一覧</a>
			</div>
		</div>
	</c:param>
=======
<%-- subject_update_done.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム - 科目変更完了</c:param>
    <c:param name="content">
        <section>
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>
            
            <%-- 画像2の②：完了メッセージ --%>
            <div class="alert alert-success mt-3">
                変更が完了しました
            </div>

            <div class="mt-3">
                <%-- 画像2の③：一覧へのリンク --%>
                <a href="SubjectList.action">科目一覧へ戻る</a>
            </div>
        </section>
    </c:param>
>>>>>>> branch 'master' of https://github.com/kikuchi0704/Exam.git
</c:import>