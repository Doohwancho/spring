package org.zerock.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {
	static {
		try {
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			Class.forName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
			log.info("####################################");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection() {
		try(Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:orcl",
				"book_ex",
				"book_ex")){
		log.info(con);
		System.out.println("success!");
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testLog4jConnection() {
		try(Connection con = DriverManager.getConnection(
				"jdbc:log4jdbc:oracle:thin:@localhost:1521:orcl",
				"book_ex",
				"book_ex")){
		log.info(con);
		System.out.println("success! ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
}
