
public class JDBCExample1 {

	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("����Ŭ JDBC ����̹� �ε� ����");
		} catch(ClassNotFoundException e) {
			System.err.println("����̹� �ε� ����"+e.getMessage());
		}
	}

}
