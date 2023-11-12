package br.com.ldnovaes.dao.util;

public interface SQLUtil {
    String getInsertSql();
    String getSelectAllSql();
    String getDeleteSQL();
	String getSelectByIdSQL();
	String getUpdateSQL();
}
