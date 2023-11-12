const botaoSelecionarTema = document.getElementById('botao-selecionar-tema');
const appRoot = document.getElementById('app-root');
const currentTheme = document.getElementById('current-theme');

botaoSelecionarTema.addEventListener('click', () => {
    appRoot.classList.toggle('dark-theme');
    
    if (appRoot.classList.contains("dark-theme")) {
		currentTheme.textContent = "light_mode";
	} else {
		currentTheme.textContent = "dark_mode";
	}
});