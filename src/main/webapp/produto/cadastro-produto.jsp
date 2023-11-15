<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form class="form" method="POST" action="produto">

	<c:if test="${not empty produto.id}">
		<div class="form-item">
			<input type="hidden" id="id" autocomplete="off" name="id" required
				value="${produto.id}">
		</div>
	</c:if>

	<div class="form-item">
		<input type="text" id="nome" autocomplete="off" name="nome" required
			value="${produto.nome}"> <label for="nome">Nome</label>
	</div>

	<input type="submit" class="button" value="Salvar" />

</form>