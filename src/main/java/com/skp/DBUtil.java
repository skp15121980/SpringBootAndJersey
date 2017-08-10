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
	static StringBuilder wildCard = new StringBuilder();

	public static List<String> getTableColumns(JdbcTemplate jdbcTemplate) throws SQLException {
		// Map<String,Integer> columns=new LinkedHashMap<String,Integer>();
		List<String> columns = new ArrayList<String>();
		//wildCard = new StringBuilder();
		// jdbcTemplate.query("select * from LTM_TASK1",new
		// ResultSetExtractor<Integer>() {

		jdbcTemplate.query(
				"select COLUMN_NAME,DATA_TYPE,COLUMN_ID from all_tab_cols where TABLE_NAME ='LTM_TASK1' ORDER by column_id",
				new ResultSetExtractor<Integer>() {

					@Override
					public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
/*
						Map<String, Integer> map = new ResultSetHelper().ResultSetHelper(rs);
						for (String key : map.keySet()) {
							Integer value = map.get(key);
							System.out.println("Key = " + key + ", Value = " + value);
						}*/
						while(rs.next()){
							columns.add(rs.getString("COLUMN_NAME"));
							wildCard.append("?,");
							String sqlTypeStr = rs.getString("DATA_TYPE");
							 String columnId=rs.getString("COLUMN_ID");
						 }
						
						rs.close();
						return 1;
						
					}
				});
		return columns;
	}

	public static StringBuilder getWildCard() {
		return wildCard.delete(wildCard.length() - 3, wildCard.length());

	}

	public static List<String> getNewColumns(List<String> uiList, List<String> backendList) {

		uiList.removeAll(backendList);
		return uiList;
	}
}
