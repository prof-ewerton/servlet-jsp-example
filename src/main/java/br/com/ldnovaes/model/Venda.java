package br.com.ldnovaes.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter	
@NoArgsConstructor
public class Venda implements Persistente{
	
	@NonNull
	private Long id;
	private Cliente cliente;
	private Produto produto;
	private Long valorTotal;

}
