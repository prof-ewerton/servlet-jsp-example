package br.com.ldnovaes.model;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter	
@NoArgsConstructor
public class Produto implements Persistente {
	
	@NonNull
	private Long id;
	private String nome;
	private List<Venda> vendas;
}
