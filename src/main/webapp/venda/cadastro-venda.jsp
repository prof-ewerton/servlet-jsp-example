<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form class="form" method="POST" action="venda">

	<c:if test="${not empty venda.id}">
		<div class="form-item">
			<input type="hidden" id="id" autocomplete="off" name="id" required
				value="${venda.id}">
		</div>
	</c:if>

	<div class="form-item">
		<input type="text" id="cliente-id" autocomplete="off" name="cliente-id" required
			value="${venda.cliente.id}"> <label for="cliente-id">ID Cliente</label>
	</div>
	
	<div class="form-item">
		<input type="text" id="produto-id" autocomplete="off" name="produto-id" required
			value="${venda.produto.id}"> <label for="produto-id">ID Produto</label>
	</div>
	
	<div class="form-item">
		<input type="text" id="valor-total" autocomplete="off" name="valor-total" required
			value="${venda.valorTotal}"> <label for="valor-total">Valor da Venda</label>
	</div>
	
	<input type="submit" class="button" value="Salvar" />

</form>