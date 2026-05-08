<%-- 科目削除JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<%-- 画面タイトル --%>
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目削除</h2>

			<div class="mx-4">
				<%-- 確認メッセージ --%>
				<p>科目名：${subject_name} （${subject_cd}） を削除しますか？</p>

				<%-- 削除処理へ送るフォーム --%>
				<form action="SubjectDeleteExecute.action" method="post">
					<%-- hidden属性（科目コード、科目名） --%>
					<input type="hidden" name="subject_cd" value="${subject_cd}">
					<input type="hidden" name="subject_name" value="${subject_name}">

					<div class="mt-4">
						<%-- 削除ボタン --%>
						<button type="submit" class="btn btn-danger">削除</button>
						
						<%-- 戻るリンク --%>
						<a href="SubjectList.action" class="ms-3">戻る</a>
					</div>
				</form>
			</div>
		</section>
	</c:param>
</c:import>