<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet" href="resources/css/inputs.css">
<link rel="stylesheet" href="resources/css/table-responsive.css">
<title>Servlet Example</title>
</head>

<body>
	<div id="app-root">
		<header>
			<div id="navbar">

				<div id="botao-menu">
					<span class="material-symbols-outlined"> menu </span>
				</div>
				<div id="logo">GERENCIAMENTO</div>
				<div id="funcoes">
					<div id="botao-selecionar-tema">
						<a href="#"> <span class="material-symbols-outlined"
							id="current-theme"> dark_mode </span>
						</a>
					</div>
				</div>
			</div>
		</header>

		<div id="container">
			<aside>
				<div id="search-menu"><i>Tudo que deve ser feito, merece ser bem feito</i></div>
				<div class="menu">
					<div class="tipo-menu">Geral</div>

					<ul id="opcoes-sidebar">
						<li><a href="home">Home</a></li>
						<li><a id="cliente" href="cliente">Clientes</a></li>
						<li><a href="#">Produtos</a></li>
					</ul>
					<div
						style="width: 100%; display: flex; align-items: center; justify-content: center;">
					</div>
				</div>
			</aside>

			<main id="main-content">
				<jsp:include page="${conteudo}" />
			</main>
			<footer>Desenvolvido por Leandro Duarte</footer>
		</div>

	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
		integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="resources/js/select-theme.js"></script>
	<script src="resources/js/sidebar-responsive.js"></script>
	<script src="resources/js/mask.js"></script>
	<script src="resources/js/ajax-custom.js"></script>
</body>

</html>