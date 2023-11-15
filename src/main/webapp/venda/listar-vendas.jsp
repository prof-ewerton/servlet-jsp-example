<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<a on class="btn-add"
	onclick="carregarPagina('venda/cadastro-venda.jsp')"> <span
	class="material-symbols-outlined icon-add"> add </span> <span>Adicionar
		Venda</span>
</a>

<form method="POST" action="venda">
	<table border="1">
		<thead>
			<tr>
				<th data-label="Cliente">Cliente</th>
				<th data-label="Produto">Produto</th>
				<th data-label="Valor">Valor</th>
				<th data-label="Ações">Ações</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${listaTodos}">
				<tr>
					<td data-label="Cliente">${item.cliente.nome}</td>
					<td data-label="Produto">${item.produto.nome}</td>
					<td data-label="Valor">${item.valorTotal}</td>
					<td data-label="Ações" class="acoes">
						<button class="btn-action btn-edit" type="button"
							onclick="editarVenda(${item.id})">
							<span class="material-symbols-outlined edit"> edit </span>
						</button>
						<button class="btn-action btn-delete" type="button"
							onclick="deletarVenda(${item.id})">
							<span class="material-symbols-outlined delete"> delete </span>
						</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</form>
