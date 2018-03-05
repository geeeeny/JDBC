import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample3 {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("상품명 입력\n>");
		String product = scanner.nextLine();
		
		scanner = new Scanner(System.in);
		System.out.print("정렬기준: ");
		String orderBy = scanner.nextLine();
		String sql = "select * from goodsinfo where name like '%" +product+ "%'"+"order by "+orderBy;
		
		System.out.println(sql);
		
		try (Connection conn = (Connection) DriverManager.getConnection(url, "iot", "1234");
				Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			System.out.println("상품코드 상품명 \t\t가격 제조사");
			System.out.println("----------------------------------------------");
			while (rs.next()) {
				String code = rs.getString("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				String maker = rs.getString("maker");
				System.out.printf("%8s %s \t%12d %s%n", code, name, price, maker);

			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}

	}
}
