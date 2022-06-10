package es.pills.hibernatetest;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcTest {

	public static void main(String[]args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/hibernate_relations?useSSL=false";
		String user = "root";
		String pass = "root";
		
		try {
			System.out.println("Trying DB Connecting...." + jdbcUrl);
			Connection myConn = DriverManager.getConnection(jdbcUrl,user,pass);
			System.out.println("Sucessfull Connection !!");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
