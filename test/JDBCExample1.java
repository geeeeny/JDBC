
public class JDBCExample1 {

	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("오라클 JDBC 드라이버 로드 성공");
		} catch(ClassNotFoundException e) {
			System.err.println("드라이버 로드 실패"+e.getMessage());
		}
	}

}
