<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<a on class="btn-add"
	onclick="carregarPagina('produto/cadastro-produto.jsp')"> <span
	class="material-symbols-outlined icon-add"> add </span> <span>Adicionar
		Produto</span>
</a>

<form method="POST" action="produto">
	<table border="1">
		<thead>
			<tr>
				<th data-label="Nome">Nome</th>
				<th data-label="Nome">Ações</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${listaTodos}">
				<tr>
					<td data-label="Nome">${item.nome}</td>
					<td data-label="Ações" class="acoes">
						<button class="btn-action btn-edit" type="button"
							onclick="editarProduto(${item.id})">
							<span class="material-symbols-outlined edit"> edit </span>
						</button>
						<button class="btn-action btn-delete" type="button"
							onclick="deletarProduto(${item.id})">
							<span class="material-symbols-outlined delete"> delete </span>
						</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</form>
