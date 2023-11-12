package br.com.ldnovaes.service;

import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;

import br.com.ldnovaes.dao.IGenericDAO;
import br.com.ldnovaes.model.Persistente;

public abstract class GenericService<T extends Persistente> implements IGenericService<T>{
	
	private IGenericDAO<T> dao;
	
	public GenericService(IGenericDAO<T> dao) {
		this.dao = dao;
	}

	@Override
	public Integer persistir(T model) {
		
		try {
			return this.dao.persistir(model);
		} catch (ServletException e) {
			System.out.println(e);
			return 0;
		}
	}	
	
	@Override
	public List<T> listarTodos() throws ServletException {
		ResultSet models =  this.dao.listarTodos();
		return this.converterResultSetParaLista(models);
	}
	
	@Override
	public Integer deletar(T model) {
		
		try {
			return this.dao.deletar(model);
		} catch (ServletException e) {
			return 0;
		}
	}
	
	@Override
	public Integer editar(T model) {
		try {
			return this.dao.editar(model);
		} catch (ServletException e) {
			return 0;
		}
	}
	
	@Override
	public T buscarPorId(Long id) throws ServletException {
		ResultSet resultSet = this.dao.buscarPorId(id);
		return this.converterResultSetParaModel(resultSet);

	}
	
	protected abstract T converterResultSetParaModel(ResultSet resultSet) throws ServletException;
	protected abstract List<T> converterResultSetParaLista(ResultSet resultSet) throws ServletException;

}
