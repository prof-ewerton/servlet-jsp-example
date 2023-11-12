function editar(id) {
	$.post('cliente', { acao: 'editar', id: id }, function(response) {
		var mainContentResponse = $(response).find('#main-content').html();
		
		// preencher dados
		
		$("#main-content").html(mainContentResponse);
		
	});
}

function deletar(id) {
	$.post('cliente', { acao: 'deletar', id: id }, function(response) {
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

function preencherCampos(cliente) {
        document.getElementById('nome').value = cliente.nome;
        document.getElementById('cpf').value = cliente.cpf;
        document.getElementById('email').value = cliente.email;
        document.getElementById('telefone').value = cliente.telefone;
    }