function editarCliente(id) {
	$.post('cliente', { acao: 'editar', id: id }, function(response) {
		var mainContentResponse = $(response).find('#main-content').html();

		$("#main-content").html(mainContentResponse);
		
	});
}

function deletarCliente(id) {
	$.post('cliente', { acao: 'deletar', id: id }, function(response) {
		recarregarDelete();
	});
}

function editarProduto(id) {
	$.post('produto', { acao: 'editar', id: id }, function(response) {
		var mainContentResponse = $(response).find('#main-content').html();
		
		$("#main-content").html(mainContentResponse);
		
	});
}


function deletarProduto(id) {
	$.post('produto', { acao: 'deletar', id: id }, function(response) {
		recarregarDelete();
	});
}


function editarVenda(id) {
	$.post('venda', { acao: 'editar', id: id }, function(response) {
		var mainContentResponse = $(response).find('#main-content').html();

		$("#main-content").html(mainContentResponse);
		
	});
}

function deletarVenda(id) {
	$.post('venda', { acao: 'deletar', id: id }, function(response) {
		recarregarDelete();
	});
}


function recarregarDelete() {
	$.get("cliente", function(response) {

		var mainContentResponse = $(response).find('#main-content').html();

		$("#main-content").html(mainContentResponse);
	});
}

function carregarPagina(pagina) {
	$('#main-content').load(pagina);
}
