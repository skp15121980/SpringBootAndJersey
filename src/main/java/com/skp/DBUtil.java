package com.skp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

public class DBUtil {
	static StringBuilder wildCard =new StringBuilder();
public static List<String> getTableColumns(JdbcTemplate jdbcTemplate) throws SQLException {
	//Map<String,Integer> columns=new LinkedHashMap<String,Integer>();
	List<String> columns = new ArrayList<String>();
	 wildCard =new StringBuilder();
	jdbcTemplate.query("select * from Person",new ResultSetExtractor<Integer>() {

	    @Override
	    public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {

	    	Map<String, Integer> map =new ResultSetHelper().ResultSetHelper(rs);
	    	for (String key : map.keySet()) {
	    	    Integer value = map.get(key);
	    	    System.out.println("Key = " + key + ", Value = " + value);
	    	}
	        ResultSetMetaData rsmd = rs.getMetaData();
	    int columnCount = rsmd.getColumnCount();
	    for(int i = 1 ; i <= columnCount ; i++){
	    /*column.setName(rsmd.getColumnName(i));
	    column.setAutoIncrement(rsmd.isAutoIncrement(i));
	    column.setType(rsmd.getColumnTypeName(i));
	    column.setTypeCode(rsmd.getColumnType(i));
	    column.setTableName(sqlTable.getName().toUpperCase());
	    columns.add(column);*/
	    	//columns.put(rsmd.getColumnName(i),rsmd.getColumnType(i));
	    	columns.add(rsmd.getColumnName(i));
	    	wildCard.append("?,");
	    }
	    rs.close();
	    return columnCount;
	}
	});
	return columns;
	}
public static StringBuilder getWildCard() {
	return wildCard.delete(wildCard.length()-3, wildCard.length());
	
}
public static List<String> getNewColumns(List<String> uiList,List<String> backendList){

uiList.removeAll( backendList );
return uiList;}
}
