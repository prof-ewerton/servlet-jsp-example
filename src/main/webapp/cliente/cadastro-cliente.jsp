<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form class="form" method="POST" action="cliente">

	<c:if test="${not empty cliente.id}">
		<div class="form-item">
			<input type="hidden" id="id" autocomplete="off" name="id" required
				value="${cliente.id}">
		</div>
	</c:if>

	<div class="form-item">
		<input type="text" id="nome" autocomplete="off" name="nome" required
			value="${cliente.nome}"> <label for="nome">Nome</label>
	</div>

	<div class="form-item">
		<input type="text" id="cpf" autocomplete="off" name="cpf"
			oninput="mask('###.###.###-##', this)" maxlength="14" required
			value="${cliente.cpf}"> <label for="cpf">CPF</label>
	</div>

	<div class="form-item">
		<input type="text" id="email" autocomplete="off" name="email" required
			value="${cliente.email}"> <label for="email">Email</label>
	</div>

	<div class="form-item">
		<input type="text" id="telefone" autocomplete="off" name="telefone"
			oninput="mask('## # ####-####', this)" maxlength="14" required
			value="${cliente.telefone}"> <label for="telefone">Telefone</label>
	</div>

	<input type="submit" class="button" value="Salvar" />

</form>