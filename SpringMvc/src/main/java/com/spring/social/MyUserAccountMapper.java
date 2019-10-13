package com.spring.social;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
 
import org.springframework.jdbc.core.RowMapper;
 
public class MyUserAccountMapper implements RowMapper<MyUserAccount> {
 
    @Override
    public MyUserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println(" ------------mapRow-----------------  MyUserAccount");

        String id = rs.getString("id");
  
        String email = rs.getString("email");
        String userName= rs.getString("user_name");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String password = rs.getString("password");
        String role = "";
        if(hasColumn(rs, "role")) {
        	role = rs.getString("role");
        }
        
		System.out.println(" ------------mapRow-----------------  MyUserAccount");
        return new MyUserAccount(id, email,userName, firstName, //
                lastName, password, //
                role );
    }
    
    public  boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columns = rsmd.getColumnCount();
        for (int x = 1; x <= columns; x++) {
            if (columnName.equals(rsmd.getColumnName(x))) {
                return true;
            }
        }
        return false;
    }
 
}