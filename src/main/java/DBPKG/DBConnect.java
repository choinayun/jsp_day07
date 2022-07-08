package DBPKG;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {

	public static Connection getConnection()throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		// 해당하는 jar 파일이 없으면 에러 발생 
		// 메세지에 대한 오타가 있을 경우 에러 발생 
		
		Connection con = DriverManager.getConnection(
			"jdbc:oracle:thin:@//localhost:1521/xe", "java2", "1234");

		return con; 
	}
	
}
