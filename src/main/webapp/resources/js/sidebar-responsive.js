const showSidebarButton = document.getElementById("botao-menu");
const sidebar = document.querySelector("#container aside");
const mainContent = document.querySelector("#main-content");

let sidebarVisible;

if (window.innerWidth <= 768) {
	sidebarVisible = false;
} else {
	sidebarVisible = true;
};

showSidebarButton.addEventListener("click", function() {
	if (sidebarVisible) {
		sidebar.style.transform = "translateX(-100%)";

		if (window.innerWidth >= 768) {
			mainContent.style.paddingLeft = "20px";
		}

	} else {
		sidebar.style.transform = "translateX(0)";

		if (window.innerWidth >= 768) {
			mainContent.style.paddingLeft = "270px";
		}
	}
	sidebarVisible = !sidebarVisible;
});

window.addEventListener("resize", () => {
	if (window.innerWidth >= 768) {
		sidebar.style.transform = "translateX(0)";
		mainContent.style.paddingLeft = "270px";
		sidebarVisible = true;
	} else {
		sidebar.style.transform = "translateX(-100%)";
		mainContent.style.paddingLeft = "20px";
		sidebarVisible = false;
	}
});

mainContent.addEventListener("click", ()=>{
	
	if (window.innerWidth <= 768 && sidebarVisible) {
		sidebar.style.transform = "translateX(-100%)";
		sidebarVisible = false;
	}
	
})



