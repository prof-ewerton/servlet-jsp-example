package br.com.ldnovaes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;

import br.com.ldnovaes.dao.util.ConnectionFactory;
import br.com.ldnovaes.dao.util.SQLUtil;
import br.com.ldnovaes.model.Persistente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class GenericDAO<T extends Persistente> implements IGenericDAO<T> {

	private String sql;
	private SQLUtil sqlUtil;
	
	public GenericDAO(SQLUtil sqlUtil) {
		this.sqlUtil = sqlUtil;
	}

	@Override
	public Integer persistir(T model) throws ServletException {

		Connection conexao = null;
		PreparedStatement stm = null;

		this.sql = this.sqlUtil.getInsertSql();

		try {
			conexao = ConnectionFactory.getConexao();
			stm = conexao.prepareStatement(this.sql);
			this.adicionarParametrosInsert(stm, model);
			return stm.executeUpdate();
		} catch (Exception e) {
			throw new ServletException(e); // metodo service, doPost e doGet só consegue capturar ServletExceptions
		} finally {
			this.fecharConexao(conexao, stm, null);
		}
	}

	@Override
	public ResultSet listarTodos() throws ServletException {
		Connection conexao = null;
		PreparedStatement stm = null;
		
		this.sql = this.sqlUtil.getSelectAllSql();
		
		try {
			conexao = ConnectionFactory.getConexao();
			stm = conexao.prepareStatement(this.sql);
			return stm.executeQuery();
		} catch(Exception e) {
			throw new ServletException(e);
		}

	}
	
	@Override
	public Integer deletar(T model) throws ServletException {
		
		Connection conexao = null;
		PreparedStatement stm = null;

		this.sql = this.sqlUtil.getDeleteSQL();
		
		try {
			conexao = ConnectionFactory.getConexao();
			stm = conexao.prepareStatement(this.sql);
			this.adicionarParametroDelete(stm, model);
			return stm.executeUpdate();
		} catch (Exception e) {
			throw new ServletException(e); // metodo service, doPost e doGet só consegue capturar ServletExceptions
		} finally {
			this.fecharConexao(conexao, stm, null);
		}

	}
	
	@Override
	public Integer editar(T model) throws ServletException {

		Connection conexao = null;
		PreparedStatement stm = null;

		this.sql = this.sqlUtil.getUpdateSQL();

		try {
			conexao = ConnectionFactory.getConexao();
			stm = conexao.prepareStatement(this.sql);
			this.adicionarParametrosUpdate(stm, model);
			return stm.executeUpdate();
		} catch (Exception e) {
			throw new ServletException(e); // metodo service, doPost e doGet só consegue capturar ServletExceptions
		} finally {
			this.fecharConexao(conexao, stm, null);
		}
	}
	
	@Override
	public ResultSet buscarPorId(Long id) throws ServletException {
		
		Connection conexao = null;
		PreparedStatement stm = null;
		
		this.sql = this.sqlUtil.getSelectByIdSQL();
		
		try {
			conexao = ConnectionFactory.getConexao();
			stm = conexao.prepareStatement(this.sql);
			this.adicionarParametroBuscaPorId(stm, id);
			return stm.executeQuery();
		} catch(Exception e) {
			throw new ServletException(e);
		}
	}

	public void fecharConexao(Connection connection, PreparedStatement stm, ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (stm != null && !stm.isClosed()) {
				stm.close();
			}
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected abstract void adicionarParametrosInsert(PreparedStatement stm, T model) throws SQLException;
	protected abstract void adicionarParametroDelete(PreparedStatement stm, T Model) throws SQLException;
	protected abstract void adicionarParametroBuscaPorId(PreparedStatement stm, Long id) throws SQLException;
	protected abstract void adicionarParametrosUpdate(PreparedStatement stm, T model) throws SQLException;
}
