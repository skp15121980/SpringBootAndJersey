package com.skp.oracle.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.skp.DBUtil;
import com.skp.oracle.domain.Person;


@Repository
public class PersonRepository {

	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	 private JdbcTemplate jdbcTemplate;

	  @Autowired
	  public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	  }
	public Person getPerson(Long personId) {
		String sql = "SELECT * FROM PERSON WHERE ID = ?";

		return (Person)jdbcTemplate.queryForObject(sql, new Object[] { personId },new BeanPropertyRowMapper(Person.class));

	}
	
	
	
   /* private static final RowMapper<Person> mapper = new RowMapper<Person>() {
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        	return new Person(rs.getLong("ID"), rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"), null);
        }
    };*/
    
    public int createPerson(Map<String,String> employee) throws SQLException {
        System.out.println("Add an employees to departmernt :" + employee.toString());
        List<String> jsonValue= new ArrayList<String>();
        
        List<String> columns =DBUtil.getTableColumns(jdbcTemplate);
        List<String> newlyAddedColumn=DBUtil.getNewColumns(jsonValue, columns);
       // String newlyAddedColumnStr=String.join(",", newlyAddedColumn);
        String columnStr=String.join(",", columns);
        StringBuilder wildCard = DBUtil.getWildCard();
        String insertSql = "INSERT INTO Person ("+columnStr+") values ( PERSON_NEXT.NEXTVAL,"+wildCard+")";
        PreparedStatementCreator ps = new PreparedStatementCreator() {
    	@Override
          public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
            final PreparedStatement ps =
                connection.prepareStatement(insertSql, new String[]{"ID"});
           
            
            /* ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmailId());
            ps.setInt(3, employee.getDepartmentId());*/
           // Connection con = DataSourceUtils.getConnection(dataSource); // your datasource
            //Statement s = connection.createStatement();
           // ResultSet rs = connection.getMetaData().getColumns(null, null, "Person", null);
           
           /* Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Person");
            List<String> columns=DBUtil.getTableColumns(jdbcTemplate);*/
          
           // ResultSet rs1 = connection.getMetaData().getColumns(null, null, "employees", null);
            //List<String> columns=DBUtil.getTableColumns("employees", connection);
            //ResultSetMetaData rsmd = rs.getMetaData();
           // while( rs.next() )
           // {    inti=0
            int i=0;
           // String[] array=jsonValue.toArray(new String[jsonValue.size()]);
            //for (String s: jsonValue) {  
                	//System.out.println( jsonValue.get(i)+" :: "+entry.getValue());
                    //ps.setObject( ++i, array[i], entry.getValue() );
                 //   ps.setObject( ++i, s);
               // }
            
            for (String key : employee.keySet()) {
        	    Object value = employee.get(key);
        	    ps.setObject( ++i, value);
        	}
            
            //}
            //return ps;
				return ps;
          }
        };

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(ps, holder);
        
        //employee.setId(holder.getKey().intValue());
        
        return holder.getKey().intValue();
      }
}
