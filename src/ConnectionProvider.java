import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//서버나 계정 등의 정보 변경시 properties 파일만 고치면 되기 때문에 유지보수가 편리하다.
public class ConnectionProvider {
	private static Connection conn;
	
	//
	public static Properties getDatabaseProperty() throws Exception{
		Properties properties = new Properties();
		String path = ConnectionProvider.class.getResource("database.properties").getPath();
		properties.load(new FileReader(path));
		
		return properties;
	}
	//
	
	static { //정적 필드의 초기화(한 줄 이상)
		try {
			Properties properties = getDatabaseProperty();
			String url = properties.getProperty("url");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");
			conn = (Connection) DriverManager.getConnection(url, username, password); 
		}catch(Exception se) {
			System.err.println(se.getMessage());
		}
	}
	
	public static Connection getConenction() {
		return conn;
	}
	
	public static void close() {
		try {
			if(conn!=null) {conn.close();}
		}catch(Exception se) {}
	}
}
