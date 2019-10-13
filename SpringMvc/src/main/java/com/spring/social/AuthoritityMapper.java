package com.spring.social;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AuthoritityMapper implements RowMapper<MyUserAccount> {

	@Override
	public MyUserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		return null;
	}

}

