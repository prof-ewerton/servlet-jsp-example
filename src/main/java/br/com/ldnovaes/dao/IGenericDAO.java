package br.com.ldnovaes.dao;

import java.sql.ResultSet;

import javax.servlet.ServletException;

import br.com.ldnovaes.model.Persistente;

public interface IGenericDAO<T extends Persistente> {
	Integer persistir(T model) throws ServletException;
	Integer deletar(T model) throws ServletException;
	Integer editar(T model) throws ServletException;
	ResultSet buscarPorId(Long id) throws ServletException;
	ResultSet listarTodos() throws ServletException;
	
}
