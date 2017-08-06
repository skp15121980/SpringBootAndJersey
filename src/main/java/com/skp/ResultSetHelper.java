package com.skp;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ResultSetHelper {

    private Map<String, Integer> columnMap;

    public  Map<String, Integer> ResultSetHelper(ResultSet rs) throws SQLException {
        this.columnMap = new HashMap<>();
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
        for (int index = 1; index <= columnCount; index++) {
            String columnName = md.getColumnLabel(index);
            if (!columnMap.containsKey(columnName)) {
                columnMap.put(columnName, index);
            }

            String tableAlias = md.getTableName(index);
            if (tableAlias != null && !tableAlias.trim().isEmpty()) {
                columnMap.put(tableAlias + "." + columnName, index);
            }
        }
		return columnMap;
    }

    public Integer getColumnIndex(String columnName) {
        return columnMap.get(columnName);
    }

    public Integer getColumnIndex(String tableAlias, String columnName) {
        return columnMap.get(tableAlias + "." + columnName);
    }

}
