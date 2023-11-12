package br.com.ldnovaes.service;

import java.util.List;

import javax.servlet.ServletException;

import br.com.ldnovaes.model.Persistente;

public interface IGenericService<T extends Persistente> {
	Integer persistir(T model);
	Integer deletar(T model);
	Integer editar(T model);
	List<T> listarTodos() throws ServletException;
	T buscarPorId(Long id) throws ServletException;
}
